package com.jinyb.crawler.cluster;

import java.util.List;



 /// <summary>
    /// �ִ����ӿ�
    /// </summary>
    public interface ITokeniser
    {
        List<String> partition(String input);//����һ������ķ�������������ַ������зִʵĴ�������һ���ַ��������顣
    }
