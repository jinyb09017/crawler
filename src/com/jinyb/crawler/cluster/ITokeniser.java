package com.jinyb.crawler.cluster;

import java.util.List;



 /// <summary>
    /// 分词器接口
    /// </summary>
    public interface ITokeniser
    {
        List<String> partition(String input);//这是一个抽象的方法，对输入的字符串进行分词的处理，返回一个字符串的数组。
    }
