package epsilon.logic;

/**
 * @author minri2
 * 
 */
public class DialogLStatementWriter{
    public static final String SPLITTER = " ";

    private StringBuilder builder;

    public void start(StringBuilder builder){
        this.builder = builder;
    }

    public void end(){
        // remove the last space
        builder.deleteCharAt(builder.length() - 1);
    }

    public DialogLStatementWriter write(Object obj) {
        return write(String.valueOf(obj)).write(SPLITTER);
    }

    public DialogLStatementWriter write(String str) {
        builder.append(str).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(StringBuffer sb) {
        builder.append(sb).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(CharSequence s) {
        builder.append(s).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(CharSequence s, int start, int end) {
        builder.append(s, start, end).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(char[] str) {
        builder.append(str).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(char[] str, int offset, int len) {
        builder.append(str, offset, len).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(boolean b) {
        builder.append(b).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(char c) {
        builder.append(c).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(int i) {
        builder.append(i).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(long lng) {
        builder.append(lng).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(float f) {
        builder.append(f).append(SPLITTER);
        return this;
    }

    public DialogLStatementWriter write(double d) {
        builder.append(d).append(SPLITTER);
        return this;
    }
}