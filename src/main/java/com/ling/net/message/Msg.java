package com.ling.net.message;

/**
 * @author zhangling  2021/12/12 20:05
 */
public abstract class Msg {

    /**
     * 客户端接收到消息后，对消息进行处理，如果是自己发送的消息，不进行处理
     */
    public abstract void handle();

    public abstract byte[] toBytes();

    public abstract void parse(byte[] bytes);

    public abstract MsgType getMsgType();
}
