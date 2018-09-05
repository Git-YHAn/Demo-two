package com.bee.devops.admin.common.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.JobWithDetails;

public class JenkinsUtils {
	/**
	 * 执行JOB
	 * @param 
	 * @return flag=1 执行成功 ，flag=0Jenkins没有正常运行,flag=2没有查询到需要执行的任务
	 * @throws IOException 
	 */
	 public static Integer buildJob(JenkinsServer jenkinsServer,JobWithDetails job,Map<String, String> params) throws URISyntaxException, IOException {
		 Map<String, Integer> map = new HashMap<>();
		 int jobflag=1;	
		 try {
			if(jenkinsServer.isRunning()){
				if(job != null){
					//jobid = job.getNextBuildNumber();
					job.build(params,false);
				}else{
					jobflag = 2;	
				}
			}else{
				jobflag = 0;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jobflag;		
	 }
	 
	 public static Integer getjenkinsjobid(JobWithDetails job) {
		int jobid = job.getNextBuildNumber();
		return jobid;		
	 }
	 
	 /**
     * @param jobId
     * @return 是否执行结束
     */
    public static boolean isJobFinish(JenkinsServer jenkinsServer,JobWithDetails job,int jobId)  {
        try{
            return job.getBuildByNumber(jobId).details().isBuilding();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
	
    /**
     * @param jobId
     * @return 获取执行结果
     */
    public static String getJobResult(JenkinsServer jenkinsServer,JobWithDetails job,int jobId)  {
        try{
            return job.getBuildByNumber(jobId).details().getResult().name();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * @param jobId
     * @return 获取执行的Console
     */
    public static String getJobConsoleOutputHtml(JenkinsServer jenkinsServer,JobWithDetails job,int jobId)  {
        try{
            return job.getBuildByNumber(jobId).details().getConsoleOutputHtml();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * @param jobId
     * @return 获取执行的Console
     */
    public static String getJobConsoleOutputText(JenkinsServer jenkinsServer,JobWithDetails job,int jobId)  {
        try{
            return job.getBuildByNumber(jobId).details().getConsoleOutputText();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * @param jobId
     * @return 停止任务
     */
    public static String stopBuildJob(JenkinsServer jenkinsServer,JobWithDetails job,int jobId)  {
        try{
            return job.getBuildByNumber(jobId).details().Stop();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
