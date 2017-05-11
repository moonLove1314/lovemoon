package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class JokeResult implements Serializable {

//    "result": [
//    {
//        "content": "每天早上，我要知道我有多么的舍不得离开你。　　好想再和你多呆一会儿，哪怕就5分钟也满足。　　但是，我还得上课，我还得忙事情。　　你理解一下我好么？晚上回来再陪你好么？　　你要知道，我深爱着你。要相信我。　　我不会变心的，我一辈子都离不开你的。　　想你，亲爱的床 。&nbsp;",
//            "hashId": "f65823b2d78d234969b49812cb18f4ec",
//            "unixtime": "1482713030",
//            "updatetime": "2016-12-26 08:43:50"
//    },
    private String content;
    private String hashId;
    private String unixtime;
    private String updatatime;

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(String updatatime) {
        this.updatatime = updatatime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
