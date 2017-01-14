package project.drawwordchain.model;

public class Statement {

    private String user;

    private String word;

    private String imgData;

    Statement(String user,String word,String imgData){
    	this.user = user;
    	this.word = word;
    	this.imgData = imgData;

    }

    public String getUser() {
        return this.user;
    }

    public String getWord() {
        return this.word;
    }

    public String getImageData() {
        return this.imgData;
    }

    // //テスト用
    // public static void main(String args[]){
    // 	Statement statement = new Statement("111","222","333");
    // 	System.out.println(statement.getUser());
    // 	System.out.println(statement.getWord());
    // 	System.out.println(statement.getImageData());
    // }
}
