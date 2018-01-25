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
 * <pre>
 * RPC API interface.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.9.0)",
    comments = "Source: api_rpc.proto")
public final class ApiServiceGrpc {

  private ApiServiceGrpc() {}

  public static final String SERVICE_NAME = "rpcpb.ApiService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetNebStateMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetNebStateResponse> METHOD_GET_NEB_STATE = getGetNebStateMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetNebStateResponse> getGetNebStateMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GetNebStateResponse> getGetNebStateMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GetNebStateResponse> getGetNebStateMethod;
    if ((getGetNebStateMethod = ApiServiceGrpc.getGetNebStateMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetNebStateMethod = ApiServiceGrpc.getGetNebStateMethod) == null) {
          ApiServiceGrpc.getGetNebStateMethod = getGetNebStateMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GetNebStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetNebState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetNebStateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetNebState"))
                  .build();
          }
        }
     }
     return getGetNebStateMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getNodeInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.NodeInfoResponse> METHOD_NODE_INFO = getNodeInfoMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.NodeInfoResponse> getNodeInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.NodeInfoResponse> getNodeInfoMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.NodeInfoResponse> getNodeInfoMethod;
    if ((getNodeInfoMethod = ApiServiceGrpc.getNodeInfoMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getNodeInfoMethod = ApiServiceGrpc.getNodeInfoMethod) == null) {
          ApiServiceGrpc.getNodeInfoMethod = getNodeInfoMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.NodeInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "NodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NodeInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("NodeInfo"))
                  .build();
          }
        }
     }
     return getNodeInfoMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getBlockDumpMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.BlockDumpRequest,
      rpcpb.ApiRpc.BlockDumpResponse> METHOD_BLOCK_DUMP = getBlockDumpMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.BlockDumpRequest,
      rpcpb.ApiRpc.BlockDumpResponse> getBlockDumpMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.BlockDumpRequest,
      rpcpb.ApiRpc.BlockDumpResponse> getBlockDumpMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.BlockDumpRequest, rpcpb.ApiRpc.BlockDumpResponse> getBlockDumpMethod;
    if ((getBlockDumpMethod = ApiServiceGrpc.getBlockDumpMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getBlockDumpMethod = ApiServiceGrpc.getBlockDumpMethod) == null) {
          ApiServiceGrpc.getBlockDumpMethod = getBlockDumpMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.BlockDumpRequest, rpcpb.ApiRpc.BlockDumpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "BlockDump"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.BlockDumpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.BlockDumpResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("BlockDump"))
                  .build();
          }
        }
     }
     return getBlockDumpMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getLatestIrreversibleBlockMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.BlockResponse> METHOD_LATEST_IRREVERSIBLE_BLOCK = getLatestIrreversibleBlockMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.BlockResponse> getLatestIrreversibleBlockMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.BlockResponse> getLatestIrreversibleBlockMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.BlockResponse> getLatestIrreversibleBlockMethod;
    if ((getLatestIrreversibleBlockMethod = ApiServiceGrpc.getLatestIrreversibleBlockMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getLatestIrreversibleBlockMethod = ApiServiceGrpc.getLatestIrreversibleBlockMethod) == null) {
          ApiServiceGrpc.getLatestIrreversibleBlockMethod = getLatestIrreversibleBlockMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "LatestIrreversibleBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("LatestIrreversibleBlock"))
                  .build();
          }
        }
     }
     return getLatestIrreversibleBlockMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getAccountsMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.AccountsResponse> METHOD_ACCOUNTS = getAccountsMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.AccountsResponse> getAccountsMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.AccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.AccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = ApiServiceGrpc.getAccountsMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getAccountsMethod = ApiServiceGrpc.getAccountsMethod) == null) {
          ApiServiceGrpc.getAccountsMethod = getAccountsMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.AccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.AccountsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Accounts"))
                  .build();
          }
        }
     }
     return getAccountsMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetAccountStateMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetAccountStateRequest,
      rpcpb.ApiRpc.GetAccountStateResponse> METHOD_GET_ACCOUNT_STATE = getGetAccountStateMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetAccountStateRequest,
      rpcpb.ApiRpc.GetAccountStateResponse> getGetAccountStateMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetAccountStateRequest,
      rpcpb.ApiRpc.GetAccountStateResponse> getGetAccountStateMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetAccountStateRequest, rpcpb.ApiRpc.GetAccountStateResponse> getGetAccountStateMethod;
    if ((getGetAccountStateMethod = ApiServiceGrpc.getGetAccountStateMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetAccountStateMethod = ApiServiceGrpc.getGetAccountStateMethod) == null) {
          ApiServiceGrpc.getGetAccountStateMethod = getGetAccountStateMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.GetAccountStateRequest, rpcpb.ApiRpc.GetAccountStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetAccountState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetAccountStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetAccountStateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetAccountState"))
                  .build();
          }
        }
     }
     return getGetAccountStateMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> METHOD_SEND_TRANSACTION = getSendTransactionMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> getSendTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> getSendTransactionMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.SendTransactionResponse> getSendTransactionMethod;
    if ((getSendTransactionMethod = ApiServiceGrpc.getSendTransactionMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSendTransactionMethod = ApiServiceGrpc.getSendTransactionMethod) == null) {
          ApiServiceGrpc.getSendTransactionMethod = getSendTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.SendTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "SendTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SendTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("SendTransaction"))
                  .build();
          }
        }
     }
     return getSendTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCallMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.CallResponse> METHOD_CALL = getCallMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.CallResponse> getCallMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.CallResponse> getCallMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.CallResponse> getCallMethod;
    if ((getCallMethod = ApiServiceGrpc.getCallMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getCallMethod = ApiServiceGrpc.getCallMethod) == null) {
          ApiServiceGrpc.getCallMethod = getCallMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.CallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.CallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Call"))
                  .build();
          }
        }
     }
     return getCallMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendRawTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendRawTransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> METHOD_SEND_RAW_TRANSACTION = getSendRawTransactionMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendRawTransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> getSendRawTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendRawTransactionRequest,
      rpcpb.ApiRpc.SendTransactionResponse> getSendRawTransactionMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.SendRawTransactionRequest, rpcpb.ApiRpc.SendTransactionResponse> getSendRawTransactionMethod;
    if ((getSendRawTransactionMethod = ApiServiceGrpc.getSendRawTransactionMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSendRawTransactionMethod = ApiServiceGrpc.getSendRawTransactionMethod) == null) {
          ApiServiceGrpc.getSendRawTransactionMethod = getSendRawTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.SendRawTransactionRequest, rpcpb.ApiRpc.SendTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "SendRawTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SendRawTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SendTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("SendRawTransaction"))
                  .build();
          }
        }
     }
     return getSendRawTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetBlockByHashMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHashRequest,
      rpcpb.ApiRpc.BlockResponse> METHOD_GET_BLOCK_BY_HASH = getGetBlockByHashMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHashRequest,
      rpcpb.ApiRpc.BlockResponse> getGetBlockByHashMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHashRequest,
      rpcpb.ApiRpc.BlockResponse> getGetBlockByHashMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHashRequest, rpcpb.ApiRpc.BlockResponse> getGetBlockByHashMethod;
    if ((getGetBlockByHashMethod = ApiServiceGrpc.getGetBlockByHashMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetBlockByHashMethod = ApiServiceGrpc.getGetBlockByHashMethod) == null) {
          ApiServiceGrpc.getGetBlockByHashMethod = getGetBlockByHashMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.GetBlockByHashRequest, rpcpb.ApiRpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetBlockByHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetBlockByHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetBlockByHash"))
                  .build();
          }
        }
     }
     return getGetBlockByHashMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetBlockByHeightMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHeightRequest,
      rpcpb.ApiRpc.BlockResponse> METHOD_GET_BLOCK_BY_HEIGHT = getGetBlockByHeightMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHeightRequest,
      rpcpb.ApiRpc.BlockResponse> getGetBlockByHeightMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHeightRequest,
      rpcpb.ApiRpc.BlockResponse> getGetBlockByHeightMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetBlockByHeightRequest, rpcpb.ApiRpc.BlockResponse> getGetBlockByHeightMethod;
    if ((getGetBlockByHeightMethod = ApiServiceGrpc.getGetBlockByHeightMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetBlockByHeightMethod = ApiServiceGrpc.getGetBlockByHeightMethod) == null) {
          ApiServiceGrpc.getGetBlockByHeightMethod = getGetBlockByHeightMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.GetBlockByHeightRequest, rpcpb.ApiRpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetBlockByHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetBlockByHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetBlockByHeight"))
                  .build();
          }
        }
     }
     return getGetBlockByHeightMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetTransactionReceiptMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetTransactionByHashRequest,
      rpcpb.ApiRpc.TransactionResponse> METHOD_GET_TRANSACTION_RECEIPT = getGetTransactionReceiptMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetTransactionByHashRequest,
      rpcpb.ApiRpc.TransactionResponse> getGetTransactionReceiptMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetTransactionByHashRequest,
      rpcpb.ApiRpc.TransactionResponse> getGetTransactionReceiptMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.GetTransactionByHashRequest, rpcpb.ApiRpc.TransactionResponse> getGetTransactionReceiptMethod;
    if ((getGetTransactionReceiptMethod = ApiServiceGrpc.getGetTransactionReceiptMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetTransactionReceiptMethod = ApiServiceGrpc.getGetTransactionReceiptMethod) == null) {
          ApiServiceGrpc.getGetTransactionReceiptMethod = getGetTransactionReceiptMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.GetTransactionByHashRequest, rpcpb.ApiRpc.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetTransactionReceipt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GetTransactionByHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetTransactionReceipt"))
                  .build();
          }
        }
     }
     return getGetTransactionReceiptMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSubscribeMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.SubscribeRequest,
      rpcpb.ApiRpc.SubscribeResponse> METHOD_SUBSCRIBE = getSubscribeMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.SubscribeRequest,
      rpcpb.ApiRpc.SubscribeResponse> getSubscribeMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.SubscribeRequest,
      rpcpb.ApiRpc.SubscribeResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.SubscribeRequest, rpcpb.ApiRpc.SubscribeResponse> getSubscribeMethod;
    if ((getSubscribeMethod = ApiServiceGrpc.getSubscribeMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSubscribeMethod = ApiServiceGrpc.getSubscribeMethod) == null) {
          ApiServiceGrpc.getSubscribeMethod = getSubscribeMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.SubscribeRequest, rpcpb.ApiRpc.SubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.SubscribeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Subscribe"))
                  .build();
          }
        }
     }
     return getSubscribeMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetGasPriceMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GasPriceResponse> METHOD_GET_GAS_PRICE = getGetGasPriceMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GasPriceResponse> getGetGasPriceMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest,
      rpcpb.ApiRpc.GasPriceResponse> getGetGasPriceMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GasPriceResponse> getGetGasPriceMethod;
    if ((getGetGasPriceMethod = ApiServiceGrpc.getGetGasPriceMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetGasPriceMethod = ApiServiceGrpc.getGetGasPriceMethod) == null) {
          ApiServiceGrpc.getGetGasPriceMethod = getGetGasPriceMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.NonParamsRequest, rpcpb.ApiRpc.GasPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetGasPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GasPriceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetGasPrice"))
                  .build();
          }
        }
     }
     return getGetGasPriceMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getEstimateGasMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.GasResponse> METHOD_ESTIMATE_GAS = getEstimateGasMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.GasResponse> getEstimateGasMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest,
      rpcpb.ApiRpc.GasResponse> getEstimateGasMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.GasResponse> getEstimateGasMethod;
    if ((getEstimateGasMethod = ApiServiceGrpc.getEstimateGasMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getEstimateGasMethod = ApiServiceGrpc.getEstimateGasMethod) == null) {
          ApiServiceGrpc.getEstimateGasMethod = getEstimateGasMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.TransactionRequest, rpcpb.ApiRpc.GasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "EstimateGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GasResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("EstimateGas"))
                  .build();
          }
        }
     }
     return getEstimateGasMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetGasUsedMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.GasResponse> METHOD_GET_GAS_USED = getGetGasUsedMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.GasResponse> getGetGasUsedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.GasResponse> getGetGasUsedMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest, rpcpb.ApiRpc.GasResponse> getGetGasUsedMethod;
    if ((getGetGasUsedMethod = ApiServiceGrpc.getGetGasUsedMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetGasUsedMethod = ApiServiceGrpc.getGetGasUsedMethod) == null) {
          ApiServiceGrpc.getGetGasUsedMethod = getGetGasUsedMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.HashRequest, rpcpb.ApiRpc.GasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetGasUsed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.HashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.GasResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetGasUsed"))
                  .build();
          }
        }
     }
     return getGetGasUsedMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetEventsByHashMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.EventsResponse> METHOD_GET_EVENTS_BY_HASH = getGetEventsByHashMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.EventsResponse> getGetEventsByHashMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest,
      rpcpb.ApiRpc.EventsResponse> getGetEventsByHashMethod() {
    io.grpc.MethodDescriptor<rpcpb.ApiRpc.HashRequest, rpcpb.ApiRpc.EventsResponse> getGetEventsByHashMethod;
    if ((getGetEventsByHashMethod = ApiServiceGrpc.getGetEventsByHashMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetEventsByHashMethod = ApiServiceGrpc.getGetEventsByHashMethod) == null) {
          ApiServiceGrpc.getGetEventsByHashMethod = getGetEventsByHashMethod = 
              io.grpc.MethodDescriptor.<rpcpb.ApiRpc.HashRequest, rpcpb.ApiRpc.EventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetEventsByHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.HashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.ApiRpc.EventsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetEventsByHash"))
                  .build();
          }
        }
     }
     return getGetEventsByHashMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ApiServiceStub newStub(io.grpc.Channel channel) {
    return new ApiServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ApiServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ApiServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ApiServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ApiServiceFutureStub(channel);
  }

  /**
   * <pre>
   * RPC API interface.
   * </pre>
   */
  public static abstract class ApiServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Return the state of the neb.
     * </pre>
     */
    public void getNebState(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetNebStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNebStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public void nodeInfo(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NodeInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNodeInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void blockDump(rpcpb.ApiRpc.BlockDumpRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockDumpResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockDumpMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void latestIrreversibleBlock(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestIrreversibleBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public void accounts(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.AccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public void getAccountState(rpcpb.ApiRpc.GetAccountStateRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetAccountStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public void sendTransaction(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public void call(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.CallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public void sendRawTransaction(rpcpb.ApiRpc.SendRawTransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendRawTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public void getBlockByHash(rpcpb.ApiRpc.GetBlockByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByHashMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public void getBlockByHeight(rpcpb.ApiRpc.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public void getTransactionReceipt(rpcpb.ApiRpc.GetTransactionByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionReceiptMethod(), responseObserver);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public void subscribe(rpcpb.ApiRpc.SubscribeRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SubscribeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public void getGasPrice(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGasPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public void estimateGas(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateGasMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public void getGasUsed(rpcpb.ApiRpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGasUsedMethod(), responseObserver);
    }

    /**
     */
    public void getEventsByHash(rpcpb.ApiRpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.EventsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEventsByHashMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNebStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.GetNebStateResponse>(
                  this, METHODID_GET_NEB_STATE)))
          .addMethod(
            getNodeInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.NodeInfoResponse>(
                  this, METHODID_NODE_INFO)))
          .addMethod(
            getBlockDumpMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.BlockDumpRequest,
                rpcpb.ApiRpc.BlockDumpResponse>(
                  this, METHODID_BLOCK_DUMP)))
          .addMethod(
            getLatestIrreversibleBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.BlockResponse>(
                  this, METHODID_LATEST_IRREVERSIBLE_BLOCK)))
          .addMethod(
            getAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.AccountsResponse>(
                  this, METHODID_ACCOUNTS)))
          .addMethod(
            getGetAccountStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.GetAccountStateRequest,
                rpcpb.ApiRpc.GetAccountStateResponse>(
                  this, METHODID_GET_ACCOUNT_STATE)))
          .addMethod(
            getSendTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.TransactionRequest,
                rpcpb.ApiRpc.SendTransactionResponse>(
                  this, METHODID_SEND_TRANSACTION)))
          .addMethod(
            getCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.TransactionRequest,
                rpcpb.ApiRpc.CallResponse>(
                  this, METHODID_CALL)))
          .addMethod(
            getSendRawTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.SendRawTransactionRequest,
                rpcpb.ApiRpc.SendTransactionResponse>(
                  this, METHODID_SEND_RAW_TRANSACTION)))
          .addMethod(
            getGetBlockByHashMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.GetBlockByHashRequest,
                rpcpb.ApiRpc.BlockResponse>(
                  this, METHODID_GET_BLOCK_BY_HASH)))
          .addMethod(
            getGetBlockByHeightMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.GetBlockByHeightRequest,
                rpcpb.ApiRpc.BlockResponse>(
                  this, METHODID_GET_BLOCK_BY_HEIGHT)))
          .addMethod(
            getGetTransactionReceiptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.GetTransactionByHashRequest,
                rpcpb.ApiRpc.TransactionResponse>(
                  this, METHODID_GET_TRANSACTION_RECEIPT)))
          .addMethod(
            getSubscribeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                rpcpb.ApiRpc.SubscribeRequest,
                rpcpb.ApiRpc.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getGetGasPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.NonParamsRequest,
                rpcpb.ApiRpc.GasPriceResponse>(
                  this, METHODID_GET_GAS_PRICE)))
          .addMethod(
            getEstimateGasMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.TransactionRequest,
                rpcpb.ApiRpc.GasResponse>(
                  this, METHODID_ESTIMATE_GAS)))
          .addMethod(
            getGetGasUsedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.HashRequest,
                rpcpb.ApiRpc.GasResponse>(
                  this, METHODID_GET_GAS_USED)))
          .addMethod(
            getGetEventsByHashMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.ApiRpc.HashRequest,
                rpcpb.ApiRpc.EventsResponse>(
                  this, METHODID_GET_EVENTS_BY_HASH)))
          .build();
    }
  }

  /**
   * <pre>
   * RPC API interface.
   * </pre>
   */
  public static final class ApiServiceStub extends io.grpc.stub.AbstractStub<ApiServiceStub> {
    private ApiServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ApiServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ApiServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ApiServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the state of the neb.
     * </pre>
     */
    public void getNebState(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetNebStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNebStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public void nodeInfo(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NodeInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNodeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void blockDump(rpcpb.ApiRpc.BlockDumpRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockDumpResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockDumpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void latestIrreversibleBlock(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestIrreversibleBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public void accounts(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.AccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public void getAccountState(rpcpb.ApiRpc.GetAccountStateRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetAccountStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public void sendTransaction(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public void call(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.CallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public void sendRawTransaction(rpcpb.ApiRpc.SendRawTransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendRawTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public void getBlockByHash(rpcpb.ApiRpc.GetBlockByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public void getBlockByHeight(rpcpb.ApiRpc.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public void getTransactionReceipt(rpcpb.ApiRpc.GetTransactionByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionReceiptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public void subscribe(rpcpb.ApiRpc.SubscribeRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SubscribeResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public void getGasPrice(rpcpb.ApiRpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGasPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public void estimateGas(rpcpb.ApiRpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public void getGasUsed(rpcpb.ApiRpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGasUsedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEventsByHash(rpcpb.ApiRpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.ApiRpc.EventsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEventsByHashMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * RPC API interface.
   * </pre>
   */
  public static final class ApiServiceBlockingStub extends io.grpc.stub.AbstractStub<ApiServiceBlockingStub> {
    private ApiServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ApiServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ApiServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ApiServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the state of the neb.
     * </pre>
     */
    public rpcpb.ApiRpc.GetNebStateResponse getNebState(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetNebStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public rpcpb.ApiRpc.NodeInfoResponse nodeInfo(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getNodeInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public rpcpb.ApiRpc.BlockDumpResponse blockDump(rpcpb.ApiRpc.BlockDumpRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockDumpMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public rpcpb.ApiRpc.BlockResponse latestIrreversibleBlock(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestIrreversibleBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public rpcpb.ApiRpc.AccountsResponse accounts(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public rpcpb.ApiRpc.GetAccountStateResponse getAccountState(rpcpb.ApiRpc.GetAccountStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public rpcpb.ApiRpc.SendTransactionResponse sendTransaction(rpcpb.ApiRpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public rpcpb.ApiRpc.CallResponse call(rpcpb.ApiRpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public rpcpb.ApiRpc.SendTransactionResponse sendRawTransaction(rpcpb.ApiRpc.SendRawTransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendRawTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public rpcpb.ApiRpc.BlockResponse getBlockByHash(rpcpb.ApiRpc.GetBlockByHashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public rpcpb.ApiRpc.BlockResponse getBlockByHeight(rpcpb.ApiRpc.GetBlockByHeightRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public rpcpb.ApiRpc.TransactionResponse getTransactionReceipt(rpcpb.ApiRpc.GetTransactionByHashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionReceiptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public java.util.Iterator<rpcpb.ApiRpc.SubscribeResponse> subscribe(
        rpcpb.ApiRpc.SubscribeRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public rpcpb.ApiRpc.GasPriceResponse getGasPrice(rpcpb.ApiRpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetGasPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public rpcpb.ApiRpc.GasResponse estimateGas(rpcpb.ApiRpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateGasMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public rpcpb.ApiRpc.GasResponse getGasUsed(rpcpb.ApiRpc.HashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetGasUsedMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.ApiRpc.EventsResponse getEventsByHash(rpcpb.ApiRpc.HashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetEventsByHashMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * RPC API interface.
   * </pre>
   */
  public static final class ApiServiceFutureStub extends io.grpc.stub.AbstractStub<ApiServiceFutureStub> {
    private ApiServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ApiServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ApiServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ApiServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Return the state of the neb.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GetNebStateResponse> getNebState(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNebStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.NodeInfoResponse> nodeInfo(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNodeInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.BlockDumpResponse> blockDump(
        rpcpb.ApiRpc.BlockDumpRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockDumpMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.BlockResponse> latestIrreversibleBlock(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestIrreversibleBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.AccountsResponse> accounts(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GetAccountStateResponse> getAccountState(
        rpcpb.ApiRpc.GetAccountStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.SendTransactionResponse> sendTransaction(
        rpcpb.ApiRpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.CallResponse> call(
        rpcpb.ApiRpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.SendTransactionResponse> sendRawTransaction(
        rpcpb.ApiRpc.SendRawTransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendRawTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.BlockResponse> getBlockByHash(
        rpcpb.ApiRpc.GetBlockByHashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.BlockResponse> getBlockByHeight(
        rpcpb.ApiRpc.GetBlockByHeightRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.TransactionResponse> getTransactionReceipt(
        rpcpb.ApiRpc.GetTransactionByHashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionReceiptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GasPriceResponse> getGasPrice(
        rpcpb.ApiRpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGasPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GasResponse> estimateGas(
        rpcpb.ApiRpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.GasResponse> getGasUsed(
        rpcpb.ApiRpc.HashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGasUsedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.ApiRpc.EventsResponse> getEventsByHash(
        rpcpb.ApiRpc.HashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEventsByHashMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_NEB_STATE = 0;
  private static final int METHODID_NODE_INFO = 1;
  private static final int METHODID_BLOCK_DUMP = 2;
  private static final int METHODID_LATEST_IRREVERSIBLE_BLOCK = 3;
  private static final int METHODID_ACCOUNTS = 4;
  private static final int METHODID_GET_ACCOUNT_STATE = 5;
  private static final int METHODID_SEND_TRANSACTION = 6;
  private static final int METHODID_CALL = 7;
  private static final int METHODID_SEND_RAW_TRANSACTION = 8;
  private static final int METHODID_GET_BLOCK_BY_HASH = 9;
  private static final int METHODID_GET_BLOCK_BY_HEIGHT = 10;
  private static final int METHODID_GET_TRANSACTION_RECEIPT = 11;
  private static final int METHODID_SUBSCRIBE = 12;
  private static final int METHODID_GET_GAS_PRICE = 13;
  private static final int METHODID_ESTIMATE_GAS = 14;
  private static final int METHODID_GET_GAS_USED = 15;
  private static final int METHODID_GET_EVENTS_BY_HASH = 16;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ApiServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ApiServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_NEB_STATE:
          serviceImpl.getNebState((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetNebStateResponse>) responseObserver);
          break;
        case METHODID_NODE_INFO:
          serviceImpl.nodeInfo((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.NodeInfoResponse>) responseObserver);
          break;
        case METHODID_BLOCK_DUMP:
          serviceImpl.blockDump((rpcpb.ApiRpc.BlockDumpRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockDumpResponse>) responseObserver);
          break;
        case METHODID_LATEST_IRREVERSIBLE_BLOCK:
          serviceImpl.latestIrreversibleBlock((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.AccountsResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_STATE:
          serviceImpl.getAccountState((rpcpb.ApiRpc.GetAccountStateRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GetAccountStateResponse>) responseObserver);
          break;
        case METHODID_SEND_TRANSACTION:
          serviceImpl.sendTransaction((rpcpb.ApiRpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse>) responseObserver);
          break;
        case METHODID_CALL:
          serviceImpl.call((rpcpb.ApiRpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.CallResponse>) responseObserver);
          break;
        case METHODID_SEND_RAW_TRANSACTION:
          serviceImpl.sendRawTransaction((rpcpb.ApiRpc.SendRawTransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SendTransactionResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_HASH:
          serviceImpl.getBlockByHash((rpcpb.ApiRpc.GetBlockByHashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_HEIGHT:
          serviceImpl.getBlockByHeight((rpcpb.ApiRpc.GetBlockByHeightRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.BlockResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_RECEIPT:
          serviceImpl.getTransactionReceipt((rpcpb.ApiRpc.GetTransactionByHashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.TransactionResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((rpcpb.ApiRpc.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.SubscribeResponse>) responseObserver);
          break;
        case METHODID_GET_GAS_PRICE:
          serviceImpl.getGasPrice((rpcpb.ApiRpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasPriceResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_GAS:
          serviceImpl.estimateGas((rpcpb.ApiRpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse>) responseObserver);
          break;
        case METHODID_GET_GAS_USED:
          serviceImpl.getGasUsed((rpcpb.ApiRpc.HashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.GasResponse>) responseObserver);
          break;
        case METHODID_GET_EVENTS_BY_HASH:
          serviceImpl.getEventsByHash((rpcpb.ApiRpc.HashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.ApiRpc.EventsResponse>) responseObserver);
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

  private static abstract class ApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ApiServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return rpcpb.ApiRpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ApiService");
    }
  }

  private static final class ApiServiceFileDescriptorSupplier
      extends ApiServiceBaseDescriptorSupplier {
    ApiServiceFileDescriptorSupplier() {}
  }

  private static final class ApiServiceMethodDescriptorSupplier
      extends ApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ApiServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ApiServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ApiServiceFileDescriptorSupplier())
              .addMethod(getGetNebStateMethod())
              .addMethod(getNodeInfoMethod())
              .addMethod(getBlockDumpMethod())
              .addMethod(getLatestIrreversibleBlockMethod())
              .addMethod(getAccountsMethod())
              .addMethod(getGetAccountStateMethod())
              .addMethod(getSendTransactionMethod())
              .addMethod(getCallMethod())
              .addMethod(getSendRawTransactionMethod())
              .addMethod(getGetBlockByHashMethod())
              .addMethod(getGetBlockByHeightMethod())
              .addMethod(getGetTransactionReceiptMethod())
              .addMethod(getSubscribeMethod())
              .addMethod(getGetGasPriceMethod())
              .addMethod(getEstimateGasMethod())
              .addMethod(getGetGasUsedMethod())
              .addMethod(getGetEventsByHashMethod())
              .build();
        }
      }
    }
    return result;
  }
}
