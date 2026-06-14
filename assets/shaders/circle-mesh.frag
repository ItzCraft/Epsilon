varying vec2 v_texCoords;
varying vec4 v_col;
varying vec3 v_position;

uniform sampler2D u_texture;
uniform vec4 u_color;

uniform vec4 u_sun_info;
uniform vec4 u_planet_info;

void main(){

    vec4 color = texture2D(u_texture, v_texCoords);

    vec3 sunPos = u_sun_info.xyz;
    vec3 planetPos = u_planet_info.xyz;
    float planetRadius = u_planet_info.w;

    vec3 point = v_position;

    vec3 rayDir = normalize(sunPos - point);
    float sunDistance = length(sunPos - point);

    vec3 oc = point - planetPos;

    float b = dot(oc, rayDir);
    float c = dot(oc, oc) - planetRadius * planetRadius;

    float discriminant = b * b - c;

    float shadow = 1.0;

    if(discriminant > 0.0){

        float root = sqrt(discriminant);

        float t1 = -b - root;
        float t2 = -b + root;

        if(
        (t1 > 0.0 && t1 < sunDistance) ||
        (t2 > 0.0 && t2 < sunDistance)
        ){
            shadow = 0.05;
        }

        float penumbra =
        smoothstep(
        planetRadius * 0.9,
        planetRadius * 1.1,
        length(cross(rayDir, planetPos - point))
        );

        shadow = mix(shadow, 1.0, penumbra);
    }

    gl_FragColor = color * u_color * v_col * vec4(vec3(shadow), 1.0);
}