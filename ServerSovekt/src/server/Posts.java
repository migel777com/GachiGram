package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Posts {
    protected List<Posts> postslist = new ArrayList<Posts>();
    private String title ="";
    private String content ="";

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Posts(String title,String content){
        this.title=title;
        this.content=content;
    }

    public void add(Posts post){
        this.postslist.add(post);
    }

    public String print(){
        return Arrays.toString(postslist.toArray());
    }

    public String feedUpdate(){
        String message = "";
        String temp = "";
        if (postslist != null && !postslist.isEmpty()) {
            for(int i=0;i<postslist.size();i++){
                temp =  "Titile: "+postslist.get(i).getTitle()+"\nContent: "+postslist.get(i).getContent()+"\n---------------------------------\n";
                message+=temp;
            }
        } else {message = "empty";}
        return message;
    }


}
