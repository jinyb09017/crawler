package com.jinyb.crawler.cluster;


    public class TermVector//文本相似度的比较
    {
        public static double ComputeCosineSimilarity(double[] vector1, double[] vector2)
        {//计算两个向量的余弦夹角，两个向量的维度必须相同。
            if (vector1.length != vector2.length)
				try {
					throw new Exception("DIFER LENGTH");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


            double denom = (VectorLength(vector1) * VectorLength(vector2));
            if (denom == 0D)
                return 0D;
            else
                return (InnerProduct(vector1, vector2) / denom);

        }

        public static double InnerProduct(double[] vector1, double[] vector2)
        {

            if (vector1.length != vector2.length)
				try {
					throw new Exception("DIFFER LENGTH ARE NOT ALLOWED");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


            double result = 0D;
            for (int i = 0; i < vector1.length; i++)
                result += vector1[i] * vector2[i];

            return result;//计算两个向量的内乘积。
        }

        public static double VectorLength(double[] vector)//计算向量的模
        {
            double sum = 0.0D;
            for (int i = 0; i < vector.length; i++)
                sum = sum + (vector[i] * vector[i]);

            return (double)Math.sqrt(sum);
        }

    }

