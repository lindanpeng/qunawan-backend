package me.lindanpeng.qunawan.web.exception;

import me.lindanpeng.qunawan.web.protocol.CodeMsg;

public class ServiceException extends RuntimeException {
    private CodeMsg codeMsg;

    public ServiceException(CodeMsg codeMsg){
        this.codeMsg=codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }


}
