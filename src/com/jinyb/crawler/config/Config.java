package com.jinyb.crawler.config;

import java.util.List;
import java.util.Map;

//����ʵ�������ö���
public class Config {
	private int deep;//ץȡ���
	private int thread;//�߳���Ŀ
	
	private String outputDir;//���Ŀ¼
	private List seeds;//����վ���б�
	
	/**
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}

	/**
	 * @return the thread
	 */
	public int getThread() {
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */
	public void setThread(int thread) {
		this.thread = thread;
	}

	/**
	 * @return the outputDir
	 */
	public String getOutputDir() {
		return outputDir;
	}

	/**
	 * @param outputDir the outputDir to set
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	/**
	 * @return the seeds
	 */
	public List getSeeds() {
		return seeds;
	}

	/**
	 * @param seeds the seeds to set
	 */
	public void setSeeds(List seeds) {
		this.seeds = seeds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Config [deep=" + deep + ", thread=" + thread + ", outputDir="
				+ outputDir + ", seeds=" + seeds + "]";
	}



}
