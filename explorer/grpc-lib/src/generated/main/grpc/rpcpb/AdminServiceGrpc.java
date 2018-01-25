package rpcpb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.0)",
    comments = "Source: api_rpc.proto")
public final class AdminServiceGrpc {

  private AdminServiceGrpc() {}

  public static final String SERVICE_NAME = "rpcpb.AdminService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getNewAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NewAccountRequest,
      rpcpb.ApiRpc.NewAccountResponse> METHOD_NEW_ACCOUNT = getNewAccountMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NewAccountRequest,
      rpcpb.ApiRpc.NewAccountResponse> getNewAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NewAccountRequest,
      rpcpb.ApiRpc.NewAccountResponse> getNewAccountMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NewAccountRequest, rpcpb.ApiRpc.NewAccountResponse> getNewAccountMethod;
    if ((getNewAccountMethod = AdminServiceGrpc.getNewAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getNewAccountMethod = AdminServiceGrpc.getNewAccountMethod) == null) {
          AdminServiceGrpc.getNewAccountMethod = getNewAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NewAccountRequest, rpcpb.ApiRpc.NewAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "NewAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NewAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NewAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("NewAccount"))
                  .build();
          }
        }
     }
     return getNewAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getUnlockAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.UnlockAccountRequest,
      rpcpb.ApiRpc.UnlockAccountResponse> METHOD_UNLOCK_ACCOUNT = getUnlockAccountMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.UnlockAccountRequest,
      rpcpb.ApiRpc.UnlockAccountResponse> getUnlockAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.UnlockAccountRequest,
      rpcpb.ApiRpc.UnlockAccountResponse> getUnlockAccountMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.UnlockAccountRequest, rpcpb.ApiRpc.UnlockAccountResponse> getUnlockAccountMethod;
    if ((getUnlockAccountMethod = AdminServiceGrpc.getUnlockAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getUnlockAccountMethod = AdminServiceGrpc.getUnlockAccountMethod) == null) {
          AdminServiceGrpc.getUnlockAccountMethod = getUnlockAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.UnlockAccountRequest, rpcpb.ApiRpc.UnlockAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "UnlockAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.UnlockAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.UnlockAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("UnlockAccount"))
                  .build();
          }
        }
     }
     return getUnlockAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getLockAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.LockAccountRequest,
      rpcpb.ApiRpc.LockAccountResponse> METHOD_LOCK_ACCOUNT = getLockAccountMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.LockAccountRequest,
      rpcpb.ApiRpc.LockAccountResponse> getLockAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.LockAccountRequest,
      rpcpb.ApiRpc.LockAccountResponse> getLockAccountMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.LockAccountRequest, rpcpb.ApiRpc.LockAccountResponse> getLockAccountMethod;
    if ((getLockAccountMethod = AdminServiceGrpc.getLockAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getLockAccountMethod = AdminServiceGrpc.getLockAccountMethod) == null) {
          AdminServiceGrpc.getLockAccountMethod = getLockAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.LockAccountRequest, rpcpb.ApiRpc.LockAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "LockAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.LockAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.LockAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("LockAccount"))
                  .build();
          }
        }
     }
     return getLockAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSignTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SignTransactionResponse> METHOD_SIGN_TRANSACTION = getSignTransactionMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SignTransactionResponse> getSignTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SignTransactionResponse> getSignTransactionMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.SignTransactionResponse> getSignTransactionMethod;
    if ((getSignTransactionMethod = AdminServiceGrpc.getSignTransactionMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getSignTransactionMethod = AdminServiceGrpc.getSignTransactionMethod) == null) {
          AdminServiceGrpc.getSignTransactionMethod = getSignTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.SignTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "SignTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SignTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("SignTransaction"))
                  .build();
          }
        }
     }
     return getSignTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendTransactionWithPassphraseMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendTransactionPassphraseRequest,
      rpcpb.ApiRpc.SendTransactionPassphraseResponse> METHOD_SEND_TRANSACTION_WITH_PASSPHRASE = getSendTransactionWithPassphraseMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendTransactionPassphraseRequest,
      rpcpb.ApiRpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendTransactionPassphraseRequest,
      rpcpb.ApiRpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendTransactionPassphraseRequest, rpcpb.ApiRpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod;
    if ((getSendTransactionWithPassphraseMethod = AdminServiceGrpc.getSendTransactionWithPassphraseMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getSendTransactionWithPassphraseMethod = AdminServiceGrpc.getSendTransactionWithPassphraseMethod) == null) {
          AdminServiceGrpc.getSendTransactionWithPassphraseMethod = getSendTransactionWithPassphraseMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.SendTransactionPassphraseRequest, rpcpb.ApiRpc.SendTransactionPassphraseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "SendTransactionWithPassphrase"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SendTransactionPassphraseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SendTransactionPassphraseResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("SendTransactionWithPassphrase"))
                  .build();
          }
        }
     }
     return getSendTransactionWithPassphraseMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStatisticsNodeInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.StatisticsNodeInfoResponse> METHOD_STATISTICS_NODE_INFO = getStatisticsNodeInfoMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod;
    if ((getStatisticsNodeInfoMethod = AdminServiceGrpc.getStatisticsNodeInfoMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStatisticsNodeInfoMethod = AdminServiceGrpc.getStatisticsNodeInfoMethod) == null) {
          AdminServiceGrpc.getStatisticsNodeInfoMethod = getStatisticsNodeInfoMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.StatisticsNodeInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StatisticsNodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.StatisticsNodeInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StatisticsNodeInfo"))
                  .build();
          }
        }
     }
     return getStatisticsNodeInfoMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDynastyMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetDynastyResponse> METHOD_GET_DYNASTY = getGetDynastyMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetDynastyResponse> getGetDynastyMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetDynastyResponse> getGetDynastyMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GetDynastyResponse> getGetDynastyMethod;
    if ((getGetDynastyMethod = AdminServiceGrpc.getGetDynastyMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGetDynastyMethod = AdminServiceGrpc.getGetDynastyMethod) == null) {
          AdminServiceGrpc.getGetDynastyMethod = getGetDynastyMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GetDynastyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "GetDynasty"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetDynastyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("GetDynasty"))
                  .build();
          }
        }
     }
     return getGetDynastyMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDelegateVotersMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetDelegateVotersRequest,
      rpcpb.ApiRpc.GetDelegateVotersResponse> METHOD_GET_DELEGATE_VOTERS = getGetDelegateVotersMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetDelegateVotersRequest,
      rpcpb.ApiRpc.GetDelegateVotersResponse> getGetDelegateVotersMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetDelegateVotersRequest,
      rpcpb.ApiRpc.GetDelegateVotersResponse> getGetDelegateVotersMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetDelegateVotersRequest, rpcpb.ApiRpc.GetDelegateVotersResponse> getGetDelegateVotersMethod;
    if ((getGetDelegateVotersMethod = AdminServiceGrpc.getGetDelegateVotersMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGetDelegateVotersMethod = AdminServiceGrpc.getGetDelegateVotersMethod) == null) {
          AdminServiceGrpc.getGetDelegateVotersMethod = getGetDelegateVotersMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.GetDelegateVotersRequest, rpcpb.ApiRpc.GetDelegateVotersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "GetDelegateVoters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetDelegateVotersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetDelegateVotersResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("GetDelegateVoters"))
                  .build();
          }
        }
     }
     return getGetDelegateVotersMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getChangeNetworkIDMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.ChangeNetworkIDRequest,
      rpcpb.ApiRpc.ChangeNetworkIDResponse> METHOD_CHANGE_NETWORK_ID = getChangeNetworkIDMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.ChangeNetworkIDRequest,
      rpcpb.ApiRpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.ChangeNetworkIDRequest,
      rpcpb.ApiRpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.ChangeNetworkIDRequest, rpcpb.ApiRpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod;
    if ((getChangeNetworkIDMethod = AdminServiceGrpc.getChangeNetworkIDMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getChangeNetworkIDMethod = AdminServiceGrpc.getChangeNetworkIDMethod) == null) {
          AdminServiceGrpc.getChangeNetworkIDMethod = getChangeNetworkIDMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.ChangeNetworkIDRequest, rpcpb.ApiRpc.ChangeNetworkIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "ChangeNetworkID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.ChangeNetworkIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.ChangeNetworkIDResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("ChangeNetworkID"))
                  .build();
          }
        }
     }
     return getChangeNetworkIDMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStartMineMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.StartMineRequest,
      rpcpb.ApiRpc.MineResponse> METHOD_START_MINE = getStartMineMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.StartMineRequest,
      rpcpb.ApiRpc.MineResponse> getStartMineMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.StartMineRequest,
      rpcpb.ApiRpc.MineResponse> getStartMineMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.StartMineRequest, rpcpb.ApiRpc.MineResponse> getStartMineMethod;
    if ((getStartMineMethod = AdminServiceGrpc.getStartMineMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStartMineMethod = AdminServiceGrpc.getStartMineMethod) == null) {
          AdminServiceGrpc.getStartMineMethod = getStartMineMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.StartMineRequest, rpcpb.ApiRpc.MineResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StartMine"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.StartMineRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.MineResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StartMine"))
                  .build();
          }
        }
     }
     return getStartMineMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStopMineMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.MineResponse> METHOD_STOP_MINE = getStopMineMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.MineResponse> getStopMineMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.MineResponse> getStopMineMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.MineResponse> getStopMineMethod;
    if ((getStopMineMethod = AdminServiceGrpc.getStopMineMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStopMineMethod = AdminServiceGrpc.getStopMineMethod) == null) {
          AdminServiceGrpc.getStopMineMethod = getStopMineMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.MineResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StopMine"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.MineResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StopMine"))
                  .build();
          }
        }
     }
     return getStopMineMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdminServiceStub newStub(io.grpc.Channel channel) {
    return new AdminServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdminServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AdminServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdminServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AdminServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AdminServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * NewAccount create a new account with passphrase
     * </pre>
     */
    public void newAccount(rpcpb.ApiRpc.NewAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NewAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public void unlockAccount(rpcpb.ApiRpc.UnlockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.UnlockAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlockAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public void lockAccount(rpcpb.ApiRpc.LockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.LockAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public void signTransaction(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SignTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public void sendTransactionWithPassphrase(rpcpb.ApiRpc.SendTransactionPassphraseRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionPassphraseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTransactionWithPassphraseMethod(), responseObserver);
    }

    /**
     */
    public void statisticsNodeInfo(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.StatisticsNodeInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStatisticsNodeInfoMethod(), responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDynastyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDynastyMethod(), responseObserver);
    }

    /**
     */
    public void getDelegateVoters(rpcpb.ApiRpc.GetDelegateVotersRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDelegateVotersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateVotersMethod(), responseObserver);
    }

    /**
     */
    public void changeNetworkID(rpcpb.ApiRpc.ChangeNetworkIDRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.ChangeNetworkIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeNetworkIDMethod(), responseObserver);
    }

    /**
     */
    public void startMine(rpcpb.ApiRpc.StartMineRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartMineMethod(), responseObserver);
    }

    /**
     */
    public void stopMine(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStopMineMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNewAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NewAccountRequest,
                rpcpb.ApiRpc.NewAccountResponse>(
                  this, METHODID_NEW_ACCOUNT)))
          .addMethod(
            getUnlockAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.UnlockAccountRequest,
                rpcpb.ApiRpc.UnlockAccountResponse>(
                  this, METHODID_UNLOCK_ACCOUNT)))
          .addMethod(
            getLockAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.LockAccountRequest,
                rpcpb.ApiRpc.LockAccountResponse>(
                  this, METHODID_LOCK_ACCOUNT)))
          .addMethod(
            getSignTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.TransactionRequest,
                rpcpb.ApiRpc.SignTransactionResponse>(
                  this, METHODID_SIGN_TRANSACTION)))
          .addMethod(
            getSendTransactionWithPassphraseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.SendTransactionPassphraseRequest,
                rpcpb.ApiRpc.SendTransactionPassphraseResponse>(
                  this, METHODID_SEND_TRANSACTION_WITH_PASSPHRASE)))
          .addMethod(
            getStatisticsNodeInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.StatisticsNodeInfoResponse>(
                  this, METHODID_STATISTICS_NODE_INFO)))
          .addMethod(
            getGetDynastyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.GetDynastyResponse>(
                  this, METHODID_GET_DYNASTY)))
          .addMethod(
            getGetDelegateVotersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.GetDelegateVotersRequest,
                rpcpb.ApiRpc.GetDelegateVotersResponse>(
                  this, METHODID_GET_DELEGATE_VOTERS)))
          .addMethod(
            getChangeNetworkIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.ChangeNetworkIDRequest,
                rpcpb.ApiRpc.ChangeNetworkIDResponse>(
                  this, METHODID_CHANGE_NETWORK_ID)))
          .addMethod(
            getStartMineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.StartMineRequest,
                rpcpb.ApiRpc.MineResponse>(
                  this, METHODID_START_MINE)))
          .addMethod(
            getStopMineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.MineResponse>(
                  this, METHODID_STOP_MINE)))
          .build();
    }
  }

  /**
   */
  public static final class AdminServiceStub extends io.grpc.stub.AbstractStub<AdminServiceStub> {
    private AdminServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * NewAccount create a new account with passphrase
     * </pre>
     */
    public void newAccount(rpcpb.ApiRpc.NewAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NewAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public void unlockAccount(rpcpb.ApiRpc.UnlockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.UnlockAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlockAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public void lockAccount(rpcpb.ApiRpc.LockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.LockAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public void signTransaction(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SignTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public void sendTransactionWithPassphrase(rpcpb.ApiRpc.SendTransactionPassphraseRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionPassphraseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTransactionWithPassphraseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void statisticsNodeInfo(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.StatisticsNodeInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStatisticsNodeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDynastyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDynastyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateVoters(rpcpb.ApiRpc.GetDelegateVotersRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDelegateVotersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateVotersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeNetworkID(rpcpb.ApiRpc.ChangeNetworkIDRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.ChangeNetworkIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeNetworkIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startMine(rpcpb.ApiRpc.StartMineRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartMineMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopMine(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStopMineMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AdminServiceBlockingStub extends io.grpc.stub.AbstractStub<AdminServiceBlockingStub> {
    private AdminServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * NewAccount create a new account with passphrase
     * </pre>
     */
    public rpcpb.ApiRpc.NewAccountResponse newAccount(rpcpb.ApiRpc.NewAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public rpcpb.ApiRpc.UnlockAccountResponse unlockAccount(rpcpb.ApiRpc.UnlockAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnlockAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public rpcpb.ApiRpc.LockAccountResponse lockAccount(rpcpb.ApiRpc.LockAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public rpcpb.ApiRpc.SignTransactionResponse signTransaction(rpcpb.ApiRpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public rpcpb.ApiRpc.SendTransactionPassphraseResponse sendTransactionWithPassphrase(rpcpb.ApiRpc.SendTransactionPassphraseRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendTransactionWithPassphraseMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.StatisticsNodeInfoResponse statisticsNodeInfo(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStatisticsNodeInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.GetDynastyResponse getDynasty(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDynastyMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.GetDelegateVotersResponse getDelegateVoters(rpcpb.ApiRpc.GetDelegateVotersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateVotersMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.ChangeNetworkIDResponse changeNetworkID(rpcpb.ApiRpc.ChangeNetworkIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangeNetworkIDMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.MineResponse startMine(rpcpb.ApiRpc.StartMineRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartMineMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.MineResponse stopMine(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStopMineMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AdminServiceFutureStub extends io.grpc.stub.AbstractStub<AdminServiceFutureStub> {
    private AdminServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdminServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdminServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * NewAccount create a new account with passphrase
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.NewAccountResponse> newAccount(
        rpcpb.ApiRpc.NewAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.UnlockAccountResponse> unlockAccount(
        rpcpb.ApiRpc.UnlockAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlockAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.LockAccountResponse> lockAccount(
        rpcpb.ApiRpc.LockAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.SignTransactionResponse> signTransaction(
        rpcpb.ApiRpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.SendTransactionPassphraseResponse> sendTransactionWithPassphrase(
        rpcpb.ApiRpc.SendTransactionPassphraseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTransactionWithPassphraseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.StatisticsNodeInfoResponse> statisticsNodeInfo(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStatisticsNodeInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GetDynastyResponse> getDynasty(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDynastyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GetDelegateVotersResponse> getDelegateVoters(
        rpcpb.ApiRpc.GetDelegateVotersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateVotersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.ChangeNetworkIDResponse> changeNetworkID(
        rpcpb.ApiRpc.ChangeNetworkIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeNetworkIDMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.MineResponse> startMine(
        rpcpb.ApiRpc.StartMineRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartMineMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.MineResponse> stopMine(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStopMineMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEW_ACCOUNT = 0;
  private static final int METHODID_UNLOCK_ACCOUNT = 1;
  private static final int METHODID_LOCK_ACCOUNT = 2;
  private static final int METHODID_SIGN_TRANSACTION = 3;
  private static final int METHODID_SEND_TRANSACTION_WITH_PASSPHRASE = 4;
  private static final int METHODID_STATISTICS_NODE_INFO = 5;
  private static final int METHODID_GET_DYNASTY = 6;
  private static final int METHODID_GET_DELEGATE_VOTERS = 7;
  private static final int METHODID_CHANGE_NETWORK_ID = 8;
  private static final int METHODID_START_MINE = 9;
  private static final int METHODID_STOP_MINE = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdminServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdminServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEW_ACCOUNT:
          serviceImpl.newAccount((rpcpb.ApiRpc.NewAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NewAccountResponse>) responseObserver);
          break;
        case METHODID_UNLOCK_ACCOUNT:
          serviceImpl.unlockAccount((rpcpb.ApiRpc.UnlockAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.UnlockAccountResponse>) responseObserver);
          break;
        case METHODID_LOCK_ACCOUNT:
          serviceImpl.lockAccount((rpcpb.ApiRpc.LockAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.LockAccountResponse>) responseObserver);
          break;
        case METHODID_SIGN_TRANSACTION:
          serviceImpl.signTransaction((rpcpb.ApiRpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SignTransactionResponse>) responseObserver);
          break;
        case METHODID_SEND_TRANSACTION_WITH_PASSPHRASE:
          serviceImpl.sendTransactionWithPassphrase((rpcpb.ApiRpc.SendTransactionPassphraseRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionPassphraseResponse>) responseObserver);
          break;
        case METHODID_STATISTICS_NODE_INFO:
          serviceImpl.statisticsNodeInfo((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.StatisticsNodeInfoResponse>) responseObserver);
          break;
        case METHODID_GET_DYNASTY:
          serviceImpl.getDynasty((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDynastyResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_VOTERS:
          serviceImpl.getDelegateVoters((rpcpb.ApiRpc.GetDelegateVotersRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetDelegateVotersResponse>) responseObserver);
          break;
        case METHODID_CHANGE_NETWORK_ID:
          serviceImpl.changeNetworkID((rpcpb.ApiRpc.ChangeNetworkIDRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.ChangeNetworkIDResponse>) responseObserver);
          break;
        case METHODID_START_MINE:
          serviceImpl.startMine((rpcpb.ApiRpc.StartMineRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse>) responseObserver);
          break;
        case METHODID_STOP_MINE:
          serviceImpl.stopMine((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.MineResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdminServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return rpcpb.ApiRpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AdminService");
    }
  }

  private static final class AdminServiceFileDescriptorSupplier
      extends AdminServiceBaseDescriptorSupplier {
    AdminServiceFileDescriptorSupplier() {}
  }

  private static final class AdminServiceMethodDescriptorSupplier
      extends AdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdminServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdminServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdminServiceFileDescriptorSupplier())
              .addMethod(getNewAccountMethod())
              .addMethod(getUnlockAccountMethod())
              .addMethod(getLockAccountMethod())
              .addMethod(getSignTransactionMethod())
              .addMethod(getSendTransactionWithPassphraseMethod())
              .addMethod(getStatisticsNodeInfoMethod())
              .addMethod(getGetDynastyMethod())
              .addMethod(getGetDelegateVotersMethod())
              .addMethod(getChangeNetworkIDMethod())
              .addMethod(getStartMineMethod())
              .addMethod(getStopMineMethod())
              .build();
        }
      }
    }
    return result;
  }
}
