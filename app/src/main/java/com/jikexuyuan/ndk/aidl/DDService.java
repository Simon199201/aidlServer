package com.jikexuyuan.ndk.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by simon on 17/3/25.
 */

public class DDService extends Service {
    private static final String TAG = "DDService";

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("DDService onCreate........" + "Thread: " + Thread.currentThread().getName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_registerParticipateCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_unregisterParticipateCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    private static final java.lang.String DESCRIPTOR = "com.jikexuyuan.ndk.aidl.IRemoteService";
    //不用aidl文件也可以实现
    private Binder binder = new Binder(){
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_basicTypes: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    long _arg1;
                    _arg1 = data.readLong();
                    boolean _arg2;
                    _arg2 = (0 != data.readInt());
                    float _arg3;
                    _arg3 = data.readFloat();
                    double _arg4;
                    _arg4 = data.readDouble();
                    String _arg5;
                    _arg5 = data.readString();
                    this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPid:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _result = this.getPid();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_registerParticipateCallback:
                {
                    data.enforceInterface(DESCRIPTOR);
                    com.jikexuyuan.ndk.aidl.IParticipateCallback _arg0;
                    _arg0 = com.jikexuyuan.ndk.aidl.IParticipateCallback.Stub.asInterface(data.readStrongBinder());
                    this.registerParticipateCallback(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_unregisterParticipateCallback:
                {
                    data.enforceInterface(DESCRIPTOR);
                    com.jikexuyuan.ndk.aidl.IParticipateCallback _arg0;
                    _arg0 = com.jikexuyuan.ndk.aidl.IParticipateCallback.Stub.asInterface(data.readStrongBinder());
                    this.unregisterParticipateCallback(_arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.e(TAG,"Thread: " + Thread.currentThread().getName());
            System.out.println("basicTypes aDouble: " + aDouble +" anInt: " + anInt+" aBoolean " + aBoolean+" aString " + aString);

        }

        public int getPid() throws RemoteException {
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("DDService getPid ");
            return Process.myPid();
        }

        public void registerParticipateCallback(IParticipateCallback cb) throws RemoteException {

        }

        public void unregisterParticipateCallback(IParticipateCallback cb) throws RemoteException {

        }


    };
    //用aidl实现
//    private final IRemoteService.Stub mBinder = new IRemoteService.Stub(){
//
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//            Log.e(TAG,"Thread: " + Thread.currentThread().getName());
//            System.out.println("basicTypes aDouble: " + aDouble +" anInt: " + anInt+" aBoolean " + aBoolean+" aString " + aString);
//
//        }
//
//        @Override
//        public int getPid() throws RemoteException {
//            System.out.println("Thread: " + Thread.currentThread().getName());
//            System.out.println("DDService getPid ");
//            return Process.myPid();
//        }
//
//        @Override
//        public void registerParticipateCallback(IParticipateCallback cb) throws RemoteException {
//
//        }
//
//        @Override
//        public void unregisterParticipateCallback(IParticipateCallback cb) throws RemoteException {
//
//        }
//    };
}
