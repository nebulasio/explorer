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
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: rpc.proto")
public final class AdminServiceGrpc {

  private AdminServiceGrpc() {}

  public static final String SERVICE_NAME = "rpcpb.AdminService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getNewAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NewAccountRequest,
      rpcpb.Rpc.NewAccountResponse> METHOD_NEW_ACCOUNT = getNewAccountMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NewAccountRequest,
      rpcpb.Rpc.NewAccountResponse> getNewAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NewAccountRequest,
      rpcpb.Rpc.NewAccountResponse> getNewAccountMethod() {
    return getNewAccountMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.NewAccountRequest,
      rpcpb.Rpc.NewAccountResponse> getNewAccountMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NewAccountRequest, rpcpb.Rpc.NewAccountResponse> getNewAccountMethod;
    if ((getNewAccountMethod = AdminServiceGrpc.getNewAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getNewAccountMethod = AdminServiceGrpc.getNewAccountMethod) == null) {
          AdminServiceGrpc.getNewAccountMethod = getNewAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NewAccountRequest, rpcpb.Rpc.NewAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "NewAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NewAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NewAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("NewAccount"))
                  .build();
          }
        }
     }
     return getNewAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getUnlockAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.UnlockAccountRequest,
      rpcpb.Rpc.UnlockAccountResponse> METHOD_UNLOCK_ACCOUNT = getUnlockAccountMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.UnlockAccountRequest,
      rpcpb.Rpc.UnlockAccountResponse> getUnlockAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.UnlockAccountRequest,
      rpcpb.Rpc.UnlockAccountResponse> getUnlockAccountMethod() {
    return getUnlockAccountMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.UnlockAccountRequest,
      rpcpb.Rpc.UnlockAccountResponse> getUnlockAccountMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.UnlockAccountRequest, rpcpb.Rpc.UnlockAccountResponse> getUnlockAccountMethod;
    if ((getUnlockAccountMethod = AdminServiceGrpc.getUnlockAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getUnlockAccountMethod = AdminServiceGrpc.getUnlockAccountMethod) == null) {
          AdminServiceGrpc.getUnlockAccountMethod = getUnlockAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.UnlockAccountRequest, rpcpb.Rpc.UnlockAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "UnlockAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.UnlockAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.UnlockAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("UnlockAccount"))
                  .build();
          }
        }
     }
     return getUnlockAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getLockAccountMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.LockAccountRequest,
      rpcpb.Rpc.LockAccountResponse> METHOD_LOCK_ACCOUNT = getLockAccountMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.LockAccountRequest,
      rpcpb.Rpc.LockAccountResponse> getLockAccountMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.LockAccountRequest,
      rpcpb.Rpc.LockAccountResponse> getLockAccountMethod() {
    return getLockAccountMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.LockAccountRequest,
      rpcpb.Rpc.LockAccountResponse> getLockAccountMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.LockAccountRequest, rpcpb.Rpc.LockAccountResponse> getLockAccountMethod;
    if ((getLockAccountMethod = AdminServiceGrpc.getLockAccountMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getLockAccountMethod = AdminServiceGrpc.getLockAccountMethod) == null) {
          AdminServiceGrpc.getLockAccountMethod = getLockAccountMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.LockAccountRequest, rpcpb.Rpc.LockAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "LockAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.LockAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.LockAccountResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("LockAccount"))
                  .build();
          }
        }
     }
     return getLockAccountMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSignTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SignTransactionResponse> METHOD_SIGN_TRANSACTION = getSignTransactionMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SignTransactionResponse> getSignTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SignTransactionResponse> getSignTransactionMethod() {
    return getSignTransactionMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SignTransactionResponse> getSignTransactionMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.SignTransactionResponse> getSignTransactionMethod;
    if ((getSignTransactionMethod = AdminServiceGrpc.getSignTransactionMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getSignTransactionMethod = AdminServiceGrpc.getSignTransactionMethod) == null) {
          AdminServiceGrpc.getSignTransactionMethod = getSignTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.SignTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "SignTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SignTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("SignTransaction"))
                  .build();
          }
        }
     }
     return getSignTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendTransactionWithPassphraseMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.SendTransactionPassphraseRequest,
      rpcpb.Rpc.SendTransactionPassphraseResponse> METHOD_SEND_TRANSACTION_WITH_PASSPHRASE = getSendTransactionWithPassphraseMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.SendTransactionPassphraseRequest,
      rpcpb.Rpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.SendTransactionPassphraseRequest,
      rpcpb.Rpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod() {
    return getSendTransactionWithPassphraseMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.SendTransactionPassphraseRequest,
      rpcpb.Rpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.SendTransactionPassphraseRequest, rpcpb.Rpc.SendTransactionPassphraseResponse> getSendTransactionWithPassphraseMethod;
    if ((getSendTransactionWithPassphraseMethod = AdminServiceGrpc.getSendTransactionWithPassphraseMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getSendTransactionWithPassphraseMethod = AdminServiceGrpc.getSendTransactionWithPassphraseMethod) == null) {
          AdminServiceGrpc.getSendTransactionWithPassphraseMethod = getSendTransactionWithPassphraseMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.SendTransactionPassphraseRequest, rpcpb.Rpc.SendTransactionPassphraseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "SendTransactionWithPassphrase"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SendTransactionPassphraseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SendTransactionPassphraseResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("SendTransactionWithPassphrase"))
                  .build();
          }
        }
     }
     return getSendTransactionWithPassphraseMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStatisticsNodeInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.StatisticsNodeInfoResponse> METHOD_STATISTICS_NODE_INFO = getStatisticsNodeInfoMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod() {
    return getStatisticsNodeInfoMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.StatisticsNodeInfoResponse> getStatisticsNodeInfoMethod;
    if ((getStatisticsNodeInfoMethod = AdminServiceGrpc.getStatisticsNodeInfoMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStatisticsNodeInfoMethod = AdminServiceGrpc.getStatisticsNodeInfoMethod) == null) {
          AdminServiceGrpc.getStatisticsNodeInfoMethod = getStatisticsNodeInfoMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.StatisticsNodeInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StatisticsNodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.StatisticsNodeInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StatisticsNodeInfo"))
                  .build();
          }
        }
     }
     return getStatisticsNodeInfoMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDynastyMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> METHOD_GET_DYNASTY = getGetDynastyMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod() {
    return getGetDynastyMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod;
    if ((getGetDynastyMethod = AdminServiceGrpc.getGetDynastyMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGetDynastyMethod = AdminServiceGrpc.getGetDynastyMethod) == null) {
          AdminServiceGrpc.getGetDynastyMethod = getGetDynastyMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetDynastyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "GetDynasty"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.ByBlockHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetDynastyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("GetDynasty"))
                  .build();
          }
        }
     }
     return getGetDynastyMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetCandidatesMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetCandidatesResponse> METHOD_GET_CANDIDATES = getGetCandidatesMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetCandidatesResponse> getGetCandidatesMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetCandidatesResponse> getGetCandidatesMethod() {
    return getGetCandidatesMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetCandidatesResponse> getGetCandidatesMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetCandidatesResponse> getGetCandidatesMethod;
    if ((getGetCandidatesMethod = AdminServiceGrpc.getGetCandidatesMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGetCandidatesMethod = AdminServiceGrpc.getGetCandidatesMethod) == null) {
          AdminServiceGrpc.getGetCandidatesMethod = getGetCandidatesMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetCandidatesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "GetCandidates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.ByBlockHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetCandidatesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("GetCandidates"))
                  .build();
          }
        }
     }
     return getGetCandidatesMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDelegateVotersMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.GetDelegateVotersRequest,
      rpcpb.Rpc.GetDelegateVotersResponse> METHOD_GET_DELEGATE_VOTERS = getGetDelegateVotersMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.GetDelegateVotersRequest,
      rpcpb.Rpc.GetDelegateVotersResponse> getGetDelegateVotersMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.GetDelegateVotersRequest,
      rpcpb.Rpc.GetDelegateVotersResponse> getGetDelegateVotersMethod() {
    return getGetDelegateVotersMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.GetDelegateVotersRequest,
      rpcpb.Rpc.GetDelegateVotersResponse> getGetDelegateVotersMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.GetDelegateVotersRequest, rpcpb.Rpc.GetDelegateVotersResponse> getGetDelegateVotersMethod;
    if ((getGetDelegateVotersMethod = AdminServiceGrpc.getGetDelegateVotersMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGetDelegateVotersMethod = AdminServiceGrpc.getGetDelegateVotersMethod) == null) {
          AdminServiceGrpc.getGetDelegateVotersMethod = getGetDelegateVotersMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.GetDelegateVotersRequest, rpcpb.Rpc.GetDelegateVotersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "GetDelegateVoters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetDelegateVotersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetDelegateVotersResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("GetDelegateVoters"))
                  .build();
          }
        }
     }
     return getGetDelegateVotersMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getChangeNetworkIDMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.ChangeNetworkIDRequest,
      rpcpb.Rpc.ChangeNetworkIDResponse> METHOD_CHANGE_NETWORK_ID = getChangeNetworkIDMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.ChangeNetworkIDRequest,
      rpcpb.Rpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.ChangeNetworkIDRequest,
      rpcpb.Rpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod() {
    return getChangeNetworkIDMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.ChangeNetworkIDRequest,
      rpcpb.Rpc.ChangeNetworkIDResponse> getChangeNetworkIDMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.ChangeNetworkIDRequest, rpcpb.Rpc.ChangeNetworkIDResponse> getChangeNetworkIDMethod;
    if ((getChangeNetworkIDMethod = AdminServiceGrpc.getChangeNetworkIDMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getChangeNetworkIDMethod = AdminServiceGrpc.getChangeNetworkIDMethod) == null) {
          AdminServiceGrpc.getChangeNetworkIDMethod = getChangeNetworkIDMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.ChangeNetworkIDRequest, rpcpb.Rpc.ChangeNetworkIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "ChangeNetworkID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.ChangeNetworkIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.ChangeNetworkIDResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("ChangeNetworkID"))
                  .build();
          }
        }
     }
     return getChangeNetworkIDMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStartMiningMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.StartMiningRequest,
      rpcpb.Rpc.MiningResponse> METHOD_START_MINING = getStartMiningMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.StartMiningRequest,
      rpcpb.Rpc.MiningResponse> getStartMiningMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.StartMiningRequest,
      rpcpb.Rpc.MiningResponse> getStartMiningMethod() {
    return getStartMiningMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.StartMiningRequest,
      rpcpb.Rpc.MiningResponse> getStartMiningMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.StartMiningRequest, rpcpb.Rpc.MiningResponse> getStartMiningMethod;
    if ((getStartMiningMethod = AdminServiceGrpc.getStartMiningMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStartMiningMethod = AdminServiceGrpc.getStartMiningMethod) == null) {
          AdminServiceGrpc.getStartMiningMethod = getStartMiningMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.StartMiningRequest, rpcpb.Rpc.MiningResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StartMining"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.StartMiningRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.MiningResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StartMining"))
                  .build();
          }
        }
     }
     return getStartMiningMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStopMiningMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.MiningResponse> METHOD_STOP_MINING = getStopMiningMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.MiningResponse> getStopMiningMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.MiningResponse> getStopMiningMethod() {
    return getStopMiningMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.MiningResponse> getStopMiningMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.MiningResponse> getStopMiningMethod;
    if ((getStopMiningMethod = AdminServiceGrpc.getStopMiningMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStopMiningMethod = AdminServiceGrpc.getStopMiningMethod) == null) {
          AdminServiceGrpc.getStopMiningMethod = getStopMiningMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.MiningResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StopMining"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.MiningResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StopMining"))
                  .build();
          }
        }
     }
     return getStopMiningMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStartPprofMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.PprofRequest,
      rpcpb.Rpc.PprofResponse> METHOD_START_PPROF = getStartPprofMethodHelper();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.PprofRequest,
      rpcpb.Rpc.PprofResponse> getStartPprofMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.PprofRequest,
      rpcpb.Rpc.PprofResponse> getStartPprofMethod() {
    return getStartPprofMethodHelper();
  }

  private static io.grpc.MethodDescriptor<rpcpb.Rpc.PprofRequest,
      rpcpb.Rpc.PprofResponse> getStartPprofMethodHelper() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.PprofRequest, rpcpb.Rpc.PprofResponse> getStartPprofMethod;
    if ((getStartPprofMethod = AdminServiceGrpc.getStartPprofMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getStartPprofMethod = AdminServiceGrpc.getStartPprofMethod) == null) {
          AdminServiceGrpc.getStartPprofMethod = getStartPprofMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.PprofRequest, rpcpb.Rpc.PprofResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.AdminService", "StartPprof"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.PprofRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.PprofResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("StartPprof"))
                  .build();
          }
        }
     }
     return getStartPprofMethod;
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
    public void newAccount(rpcpb.Rpc.NewAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.NewAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewAccountMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public void unlockAccount(rpcpb.Rpc.UnlockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.UnlockAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlockAccountMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public void lockAccount(rpcpb.Rpc.LockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.LockAccountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockAccountMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public void signTransaction(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SignTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignTransactionMethodHelper(), responseObserver);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public void sendTransactionWithPassphrase(rpcpb.Rpc.SendTransactionPassphraseRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionPassphraseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTransactionWithPassphraseMethodHelper(), responseObserver);
    }

    /**
     */
    public void statisticsNodeInfo(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.StatisticsNodeInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStatisticsNodeInfoMethodHelper(), responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDynastyMethodHelper(), responseObserver);
    }

    /**
     */
    public void getCandidates(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetCandidatesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCandidatesMethodHelper(), responseObserver);
    }

    /**
     */
    public void getDelegateVoters(rpcpb.Rpc.GetDelegateVotersRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDelegateVotersResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegateVotersMethodHelper(), responseObserver);
    }

    /**
     */
    public void changeNetworkID(rpcpb.Rpc.ChangeNetworkIDRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.ChangeNetworkIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeNetworkIDMethodHelper(), responseObserver);
    }

    /**
     */
    public void startMining(rpcpb.Rpc.StartMiningRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartMiningMethodHelper(), responseObserver);
    }

    /**
     */
    public void stopMining(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStopMiningMethodHelper(), responseObserver);
    }

    /**
     */
    public void startPprof(rpcpb.Rpc.PprofRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.PprofResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartPprofMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNewAccountMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NewAccountRequest,
                rpcpb.Rpc.NewAccountResponse>(
                  this, METHODID_NEW_ACCOUNT)))
          .addMethod(
            getUnlockAccountMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.UnlockAccountRequest,
                rpcpb.Rpc.UnlockAccountResponse>(
                  this, METHODID_UNLOCK_ACCOUNT)))
          .addMethod(
            getLockAccountMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.LockAccountRequest,
                rpcpb.Rpc.LockAccountResponse>(
                  this, METHODID_LOCK_ACCOUNT)))
          .addMethod(
            getSignTransactionMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.TransactionRequest,
                rpcpb.Rpc.SignTransactionResponse>(
                  this, METHODID_SIGN_TRANSACTION)))
          .addMethod(
            getSendTransactionWithPassphraseMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.SendTransactionPassphraseRequest,
                rpcpb.Rpc.SendTransactionPassphraseResponse>(
                  this, METHODID_SEND_TRANSACTION_WITH_PASSPHRASE)))
          .addMethod(
            getStatisticsNodeInfoMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.StatisticsNodeInfoResponse>(
                  this, METHODID_STATISTICS_NODE_INFO)))
          .addMethod(
            getGetDynastyMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.ByBlockHeightRequest,
                rpcpb.Rpc.GetDynastyResponse>(
                  this, METHODID_GET_DYNASTY)))
          .addMethod(
            getGetCandidatesMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.ByBlockHeightRequest,
                rpcpb.Rpc.GetCandidatesResponse>(
                  this, METHODID_GET_CANDIDATES)))
          .addMethod(
            getGetDelegateVotersMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.GetDelegateVotersRequest,
                rpcpb.Rpc.GetDelegateVotersResponse>(
                  this, METHODID_GET_DELEGATE_VOTERS)))
          .addMethod(
            getChangeNetworkIDMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.ChangeNetworkIDRequest,
                rpcpb.Rpc.ChangeNetworkIDResponse>(
                  this, METHODID_CHANGE_NETWORK_ID)))
          .addMethod(
            getStartMiningMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.StartMiningRequest,
                rpcpb.Rpc.MiningResponse>(
                  this, METHODID_START_MINING)))
          .addMethod(
            getStopMiningMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.MiningResponse>(
                  this, METHODID_STOP_MINING)))
          .addMethod(
            getStartPprofMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.PprofRequest,
                rpcpb.Rpc.PprofResponse>(
                  this, METHODID_START_PPROF)))
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
    public void newAccount(rpcpb.Rpc.NewAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.NewAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewAccountMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public void unlockAccount(rpcpb.Rpc.UnlockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.UnlockAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlockAccountMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public void lockAccount(rpcpb.Rpc.LockAccountRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.LockAccountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockAccountMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public void signTransaction(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SignTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignTransactionMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public void sendTransactionWithPassphrase(rpcpb.Rpc.SendTransactionPassphraseRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionPassphraseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTransactionWithPassphraseMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void statisticsNodeInfo(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.StatisticsNodeInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStatisticsNodeInfoMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDynastyMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCandidates(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetCandidatesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCandidatesMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegateVoters(rpcpb.Rpc.GetDelegateVotersRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDelegateVotersResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegateVotersMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeNetworkID(rpcpb.Rpc.ChangeNetworkIDRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.ChangeNetworkIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeNetworkIDMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startMining(rpcpb.Rpc.StartMiningRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartMiningMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopMining(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStopMiningMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startPprof(rpcpb.Rpc.PprofRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.PprofResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartPprofMethodHelper(), getCallOptions()), request, responseObserver);
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
    public rpcpb.Rpc.NewAccountResponse newAccount(rpcpb.Rpc.NewAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewAccountMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public rpcpb.Rpc.UnlockAccountResponse unlockAccount(rpcpb.Rpc.UnlockAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnlockAccountMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public rpcpb.Rpc.LockAccountResponse lockAccount(rpcpb.Rpc.LockAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockAccountMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public rpcpb.Rpc.SignTransactionResponse signTransaction(rpcpb.Rpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignTransactionMethodHelper(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public rpcpb.Rpc.SendTransactionPassphraseResponse sendTransactionWithPassphrase(rpcpb.Rpc.SendTransactionPassphraseRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendTransactionWithPassphraseMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.StatisticsNodeInfoResponse statisticsNodeInfo(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStatisticsNodeInfoMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.GetDynastyResponse getDynasty(rpcpb.Rpc.ByBlockHeightRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDynastyMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.GetCandidatesResponse getCandidates(rpcpb.Rpc.ByBlockHeightRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCandidatesMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.GetDelegateVotersResponse getDelegateVoters(rpcpb.Rpc.GetDelegateVotersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegateVotersMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.ChangeNetworkIDResponse changeNetworkID(rpcpb.Rpc.ChangeNetworkIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getChangeNetworkIDMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.MiningResponse startMining(rpcpb.Rpc.StartMiningRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartMiningMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.MiningResponse stopMining(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getStopMiningMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.PprofResponse startPprof(rpcpb.Rpc.PprofRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartPprofMethodHelper(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.NewAccountResponse> newAccount(
        rpcpb.Rpc.NewAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewAccountMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * UnlockAccount unlock account with passphrase
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.UnlockAccountResponse> unlockAccount(
        rpcpb.Rpc.UnlockAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlockAccountMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * LockAccount lock account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.LockAccountResponse> lockAccount(
        rpcpb.Rpc.LockAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockAccountMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sign sign transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.SignTransactionResponse> signTransaction(
        rpcpb.Rpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignTransactionMethodHelper(), getCallOptions()), request);
    }

    /**
     * <pre>
     * SendTransactionWithPassphrase send transaction with passphrase
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.SendTransactionPassphraseResponse> sendTransactionWithPassphrase(
        rpcpb.Rpc.SendTransactionPassphraseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTransactionWithPassphraseMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.StatisticsNodeInfoResponse> statisticsNodeInfo(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStatisticsNodeInfoMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetDynastyResponse> getDynasty(
        rpcpb.Rpc.ByBlockHeightRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDynastyMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetCandidatesResponse> getCandidates(
        rpcpb.Rpc.ByBlockHeightRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCandidatesMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetDelegateVotersResponse> getDelegateVoters(
        rpcpb.Rpc.GetDelegateVotersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegateVotersMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.ChangeNetworkIDResponse> changeNetworkID(
        rpcpb.Rpc.ChangeNetworkIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeNetworkIDMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.MiningResponse> startMining(
        rpcpb.Rpc.StartMiningRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartMiningMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.MiningResponse> stopMining(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStopMiningMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.PprofResponse> startPprof(
        rpcpb.Rpc.PprofRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartPprofMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NEW_ACCOUNT = 0;
  private static final int METHODID_UNLOCK_ACCOUNT = 1;
  private static final int METHODID_LOCK_ACCOUNT = 2;
  private static final int METHODID_SIGN_TRANSACTION = 3;
  private static final int METHODID_SEND_TRANSACTION_WITH_PASSPHRASE = 4;
  private static final int METHODID_STATISTICS_NODE_INFO = 5;
  private static final int METHODID_GET_DYNASTY = 6;
  private static final int METHODID_GET_CANDIDATES = 7;
  private static final int METHODID_GET_DELEGATE_VOTERS = 8;
  private static final int METHODID_CHANGE_NETWORK_ID = 9;
  private static final int METHODID_START_MINING = 10;
  private static final int METHODID_STOP_MINING = 11;
  private static final int METHODID_START_PPROF = 12;

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
          serviceImpl.newAccount((rpcpb.Rpc.NewAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.NewAccountResponse>) responseObserver);
          break;
        case METHODID_UNLOCK_ACCOUNT:
          serviceImpl.unlockAccount((rpcpb.Rpc.UnlockAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.UnlockAccountResponse>) responseObserver);
          break;
        case METHODID_LOCK_ACCOUNT:
          serviceImpl.lockAccount((rpcpb.Rpc.LockAccountRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.LockAccountResponse>) responseObserver);
          break;
        case METHODID_SIGN_TRANSACTION:
          serviceImpl.signTransaction((rpcpb.Rpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.SignTransactionResponse>) responseObserver);
          break;
        case METHODID_SEND_TRANSACTION_WITH_PASSPHRASE:
          serviceImpl.sendTransactionWithPassphrase((rpcpb.Rpc.SendTransactionPassphraseRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionPassphraseResponse>) responseObserver);
          break;
        case METHODID_STATISTICS_NODE_INFO:
          serviceImpl.statisticsNodeInfo((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.StatisticsNodeInfoResponse>) responseObserver);
          break;
        case METHODID_GET_DYNASTY:
          serviceImpl.getDynasty((rpcpb.Rpc.ByBlockHeightRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse>) responseObserver);
          break;
        case METHODID_GET_CANDIDATES:
          serviceImpl.getCandidates((rpcpb.Rpc.ByBlockHeightRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetCandidatesResponse>) responseObserver);
          break;
        case METHODID_GET_DELEGATE_VOTERS:
          serviceImpl.getDelegateVoters((rpcpb.Rpc.GetDelegateVotersRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDelegateVotersResponse>) responseObserver);
          break;
        case METHODID_CHANGE_NETWORK_ID:
          serviceImpl.changeNetworkID((rpcpb.Rpc.ChangeNetworkIDRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.ChangeNetworkIDResponse>) responseObserver);
          break;
        case METHODID_START_MINING:
          serviceImpl.startMining((rpcpb.Rpc.StartMiningRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse>) responseObserver);
          break;
        case METHODID_STOP_MINING:
          serviceImpl.stopMining((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.MiningResponse>) responseObserver);
          break;
        case METHODID_START_PPROF:
          serviceImpl.startPprof((rpcpb.Rpc.PprofRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.PprofResponse>) responseObserver);
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
      return rpcpb.Rpc.getDescriptor();
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
              .addMethod(getNewAccountMethodHelper())
              .addMethod(getUnlockAccountMethodHelper())
              .addMethod(getLockAccountMethodHelper())
              .addMethod(getSignTransactionMethodHelper())
              .addMethod(getSendTransactionWithPassphraseMethodHelper())
              .addMethod(getStatisticsNodeInfoMethodHelper())
              .addMethod(getGetDynastyMethodHelper())
              .addMethod(getGetCandidatesMethodHelper())
              .addMethod(getGetDelegateVotersMethodHelper())
              .addMethod(getChangeNetworkIDMethodHelper())
              .addMethod(getStartMiningMethodHelper())
              .addMethod(getStopMiningMethodHelper())
              .addMethod(getStartPprofMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
