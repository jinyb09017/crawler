package com.jinyb.crawler.config;

import java.util.List;
import java.util.Map;

//用于实例化配置对象
public class Config {
	private int deep;//抓取深度
	private int thread;//线程数目
	
	private String outputDir;//输出目录
	private List seeds;//种子站点列表
	
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
