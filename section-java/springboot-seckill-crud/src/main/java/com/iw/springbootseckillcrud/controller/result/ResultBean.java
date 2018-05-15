package com.iw.springbootseckillcrud.controller.result;

import javax.xml.transform.Result;

/**
 * 处理 ajax 调用的结果类
 * @author Lvhoufa
 */
public class ResultBean {

    private String message;
    private String status;
    private Object result;

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";

    /**
     * 请求成功
     * @param result    返回处理成功对象
     * @return
     */
    public static ResultBean success(Object result){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(STATUS_SUCCESS);
        resultBean.setResult(result);
        return resultBean;
    }

    public static ResultBean success(){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(STATUS_SUCCESS);
        return resultBean;
    }


    /**
     * 请求失败
     * @param message   返回失败信息
     * @return
     */
    public static ResultBean error(String message){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatus(STATUS_ERROR);
        resultBean.setMessage(message);
        return resultBean;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
