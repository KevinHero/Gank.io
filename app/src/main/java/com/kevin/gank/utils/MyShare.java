package com.kevin.gank.utils;

public interface MyShare {
    
    /**
     * 注意@后要有空格
     * 除微信、朋友圈、腾讯微博外的默认分享内容
     */
    String SHARE_OFFICIAL_SINA_WEIBO = " @喂车车 ";
    String SHARE_DEFAULT_TEXT = "这年头什么稀罕事都有，连加油也可以团购了，喂车君是你干的么？";
    String SHARE_DEFAULT_TEXT_WEIBO = SHARE_DEFAULT_TEXT + SHARE_OFFICIAL_SINA_WEIBO;
    String SHARE_GROUPON_DETAIL_URL = "http://www.weicheche.cn/gpns/detail/id/";
    String SHARE_GASSTATION_DETAIL_URL = "http://www.weicheche.cn/station/detail/id/";
    String SHARE_GROUPON_ORDER_DETAIL_URL = "http://www.weicheche.cn/gpns/order/code/";
    
    /**
     * 朋友圈分享标题
     */
    String SHARE_CIRCLE_TITLE = "加油团购，你想过么";

    /**
     * 朋友圈分享内容
     */
    String SHARE_CIRCLE_TEXT = "油价6元时代，从喂车车开始。 www.weicheche.cn";

    /**
     * 微信分享标题
     */
    String SHARE_WECHAT_TITLE = "加油团购，你想过么";
	
	/**
     * 微信分享内容
     */
    String SHARE_WECHAT_TEXT = "油价6元时代，从喂车车开始。 www.weicheche.cn";
	
	/**
     * 邮件分享主题
     */
    String SHARE_MAIL_SUBJECT = "加油团购，岂止便宜？";
    
    /**
     * 团购订单的分享
     */
    String SHARE_GROUP_ORDER = "【喂车车】加油团购订单。";
    
    /**
     * 团购券的分享
     */
    String SHARE_GROUP_DETAIL = "我在【喂车车】发现了一个不错的加油团购券哦，你也来看看吧。";
    
    /**
     * 团购券详情的分享title
     */
    String SHARE_GROUP_DETAIL_TITLE = "加油可以更便宜";
    
    
    //==============================首页====================================//
    /**
     * 首页中的分享标题
     */
    String HOME_PAGE_SHARE_TITLE = "加油团购，岂止便宜？";
    
    /**
     * 首页中微博分享内容
     */
    String HOME_PAGE_SHARE_WEIBO_CONTENT = "加油省钱，从 @喂车车 开始。戳这里：www.weicheche.cn";
    
    /**
     * 首页中微信分享内容
     */
    String HOME_PAGE_SHARE_WEIXIN_CONTENT = "忽如一夜春风来，油价回到五年前；改革之风吹满地，喂车送你大便宜。";
    
    /**
     * 首页中朋友圈分享内容
     */
    String HOME_PAGE_SHARE_CIRCLE_CONTENT = "";
    
    /**
     * 首页中默认分享内容
     */
    String HOME_PAGE_SHARE_CONTENT = "忽如一夜春风来，油价回到五年前；改革之风吹满地，喂车送你大便宜。戳这里：www.weicheche.cn，带你装壕带你飞。";
    
    
    
}
