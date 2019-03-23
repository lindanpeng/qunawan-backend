package me.lindanpeng.qunawan.api.exception;

import me.lindanpeng.qunawan.api.protocol.CodeMsg;

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
