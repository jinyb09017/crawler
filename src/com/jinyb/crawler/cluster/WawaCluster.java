package com.jinyb.crawler.cluster;

import java.util.ArrayList;
import java.util.List;

     public class WawaCluster
    {
    	 
    	private List<Integer> currentMembership = new ArrayList<Integer>();
        public WawaCluster(int dataindex,double[] data)
        {
            currentMembership.add(dataindex);
            Mean = data;
        }

        /// <summary>
        /// �þ�������ݳ�Ա����
        /// </summary>
         
        /* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
	

		/// <summary>
        /// �þ��������
        /// </summary>
         double[] Mean;
         /**
		 * @return the themeWord
		 */
		public String getThemeWord() {
			return themeWord;
		}
		/**
		 * @param themeWord the themeWord to set
		 */
		public void setThemeWord(String themeWord) {
			this.themeWord = themeWord;
		}
		/**
		 * @return the currentMembership
		 */
		public List<Integer> getCurrentMembership() {
			return currentMembership;
		}
		/**
		 * @param currentMembership the currentMembership to set
		 */
		public void setCurrentMembership(List<Integer> currentMembership) {
			this.currentMembership = currentMembership;
		}

		String themeWord = new String();//���ڴ洢ÿ�������г��ִ������Ĵʣ����������������Ĵ�����˼
        /// <summary>
        /// �÷�������������ľ�ֵ //
        /// </summary>
        /// <param name="coordinates"></param>
        public void UpdateMean(double[][] coordinates)
        {
            // ���� mCurrentMembership ȡ��ԭʼ���ϵ���� coord ���ö����� coordinates ��һ���Ӽ���
            //Ȼ��ȡ�����Ӽ��ľ�ֵ��ȡ��ֵ���㷨�ܼ򵥣����԰� coordinates �����һ�� m*n �ľ��� ,
            //ÿ����ֵ����ÿ�������е�ȡ��ƽ��ֵ , //��ֵ������ mCenter ��

            for (int i = 0; i < currentMembership.size(); i++)
            {
                double[] coord = coordinates[currentMembership.get(i)];
                for (int j = 0; j < coord.length; j++)
                {
                    Mean[j] += coord[j]; // �õ�ÿ�������еĺͣ�
                }
                for (int k = 0; k < Mean.length; k++)
                {
                    Mean[k] /= coord.length; // ��ÿ��������ȡƽ��ֵ
                }
            }
        }
    }

