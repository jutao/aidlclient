/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\AndroidStudio\\Android\\workspace\\MyApplication\\aidlclient\\src\\main\\aidl\\com\\example\\jutao\\aidl\\PersonAidl.aidl
 */
package com.example.jutao.aidl;
public interface PersonAidl extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.jutao.aidl.PersonAidl
{
private static final java.lang.String DESCRIPTOR = "com.example.jutao.aidl.PersonAidl";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.jutao.aidl.PersonAidl interface,
 * generating a proxy if needed.
 */
public static com.example.jutao.aidl.PersonAidl asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.jutao.aidl.PersonAidl))) {
return ((com.example.jutao.aidl.PersonAidl)iin);
}
return new com.example.jutao.aidl.PersonAidl.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_add:
{
data.enforceInterface(DESCRIPTOR);
com.example.jutao.aidl.Person _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.jutao.aidl.Person.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.util.List<com.example.jutao.aidl.Person> _result = this.add(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.jutao.aidl.PersonAidl
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.util.List<com.example.jutao.aidl.Person> add(com.example.jutao.aidl.Person person) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.example.jutao.aidl.Person> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((person!=null)) {
_data.writeInt(1);
person.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_add, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.example.jutao.aidl.Person.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_add = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.util.List<com.example.jutao.aidl.Person> add(com.example.jutao.aidl.Person person) throws android.os.RemoteException;
}
