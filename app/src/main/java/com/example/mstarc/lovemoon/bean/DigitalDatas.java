package com.example.mstarc.lovemoon.bean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-27.
 */

public class DigitalDatas {

    public String error_code;
    public String reason;
    public Result result;

    public class Result{
        public GetRankTypeRsp GetRankTypeRsp;
    }
    public class GetRankTypeRsp{
        public RankList RankList;
        public RankTimeList RankTimeList;
        public ContentTypeList ContentTypeList;
    }
    public class RankList{
        public List<Rank> Rank;
    }
    public class RankTimeList{
        public List<RankTime> RankTime;

    }
    public class ContentTypeList{
        public List<ContentType> ContentType;
    }
    public class Rank{
        public String rankTypeID;
        public String rankName;
    }
    public class RankTime{

        public String rankTimeID;
        public String rankTimeName;
    }
    public class ContentType{

        public String contentTypeID;
        public String  contentType;
    }
}
