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
    comments = "Source: rpc.proto")
public final class ApiServiceGrpc {

  private ApiServiceGrpc() {}

  public static final String SERVICE_NAME = "rpcpb.ApiService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetNebStateMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GetNebStateResponse> METHOD_GET_NEB_STATE = getGetNebStateMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GetNebStateResponse> getGetNebStateMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GetNebStateResponse> getGetNebStateMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.GetNebStateResponse> getGetNebStateMethod;
    if ((getGetNebStateMethod = ApiServiceGrpc.getGetNebStateMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetNebStateMethod = ApiServiceGrpc.getGetNebStateMethod) == null) {
          ApiServiceGrpc.getGetNebStateMethod = getGetNebStateMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.GetNebStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetNebState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetNebStateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetNebState"))
                  .build();
          }
        }
     }
     return getGetNebStateMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getNodeInfoMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.NodeInfoResponse> METHOD_NODE_INFO = getNodeInfoMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.NodeInfoResponse> getNodeInfoMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.NodeInfoResponse> getNodeInfoMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.NodeInfoResponse> getNodeInfoMethod;
    if ((getNodeInfoMethod = ApiServiceGrpc.getNodeInfoMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getNodeInfoMethod = ApiServiceGrpc.getNodeInfoMethod) == null) {
          ApiServiceGrpc.getNodeInfoMethod = getNodeInfoMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.NodeInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "NodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NodeInfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("NodeInfo"))
                  .build();
          }
        }
     }
     return getNodeInfoMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getBlockDumpMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.BlockDumpRequest,
      rpcpb.Rpc.BlockDumpResponse> METHOD_BLOCK_DUMP = getBlockDumpMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.BlockDumpRequest,
      rpcpb.Rpc.BlockDumpResponse> getBlockDumpMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.BlockDumpRequest,
      rpcpb.Rpc.BlockDumpResponse> getBlockDumpMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.BlockDumpRequest, rpcpb.Rpc.BlockDumpResponse> getBlockDumpMethod;
    if ((getBlockDumpMethod = ApiServiceGrpc.getBlockDumpMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getBlockDumpMethod = ApiServiceGrpc.getBlockDumpMethod) == null) {
          ApiServiceGrpc.getBlockDumpMethod = getBlockDumpMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.BlockDumpRequest, rpcpb.Rpc.BlockDumpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "BlockDump"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.BlockDumpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.BlockDumpResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("BlockDump"))
                  .build();
          }
        }
     }
     return getBlockDumpMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getLatestIrreversibleBlockMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.BlockResponse> METHOD_LATEST_IRREVERSIBLE_BLOCK = getLatestIrreversibleBlockMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.BlockResponse> getLatestIrreversibleBlockMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.BlockResponse> getLatestIrreversibleBlockMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.BlockResponse> getLatestIrreversibleBlockMethod;
    if ((getLatestIrreversibleBlockMethod = ApiServiceGrpc.getLatestIrreversibleBlockMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getLatestIrreversibleBlockMethod = ApiServiceGrpc.getLatestIrreversibleBlockMethod) == null) {
          ApiServiceGrpc.getLatestIrreversibleBlockMethod = getLatestIrreversibleBlockMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "LatestIrreversibleBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("LatestIrreversibleBlock"))
                  .build();
          }
        }
     }
     return getLatestIrreversibleBlockMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getAccountsMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.AccountsResponse> METHOD_ACCOUNTS = getAccountsMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.AccountsResponse> getAccountsMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.AccountsResponse> getAccountsMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.AccountsResponse> getAccountsMethod;
    if ((getAccountsMethod = ApiServiceGrpc.getAccountsMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getAccountsMethod = ApiServiceGrpc.getAccountsMethod) == null) {
          ApiServiceGrpc.getAccountsMethod = getAccountsMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.AccountsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Accounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.AccountsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Accounts"))
                  .build();
          }
        }
     }
     return getAccountsMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetAccountStateMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.GetAccountStateRequest,
      rpcpb.Rpc.GetAccountStateResponse> METHOD_GET_ACCOUNT_STATE = getGetAccountStateMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.GetAccountStateRequest,
      rpcpb.Rpc.GetAccountStateResponse> getGetAccountStateMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.GetAccountStateRequest,
      rpcpb.Rpc.GetAccountStateResponse> getGetAccountStateMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.GetAccountStateRequest, rpcpb.Rpc.GetAccountStateResponse> getGetAccountStateMethod;
    if ((getGetAccountStateMethod = ApiServiceGrpc.getGetAccountStateMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetAccountStateMethod = ApiServiceGrpc.getGetAccountStateMethod) == null) {
          ApiServiceGrpc.getGetAccountStateMethod = getGetAccountStateMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.GetAccountStateRequest, rpcpb.Rpc.GetAccountStateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetAccountState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetAccountStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetAccountStateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetAccountState"))
                  .build();
          }
        }
     }
     return getGetAccountStateMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> METHOD_SEND_TRANSACTION = getSendTransactionMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> getSendTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> getSendTransactionMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.SendTransactionResponse> getSendTransactionMethod;
    if ((getSendTransactionMethod = ApiServiceGrpc.getSendTransactionMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSendTransactionMethod = ApiServiceGrpc.getSendTransactionMethod) == null) {
          ApiServiceGrpc.getSendTransactionMethod = getSendTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.SendTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "SendTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SendTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("SendTransaction"))
                  .build();
          }
        }
     }
     return getSendTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCallMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.CallResponse> METHOD_CALL = getCallMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.CallResponse> getCallMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.CallResponse> getCallMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.CallResponse> getCallMethod;
    if ((getCallMethod = ApiServiceGrpc.getCallMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getCallMethod = ApiServiceGrpc.getCallMethod) == null) {
          ApiServiceGrpc.getCallMethod = getCallMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.CallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.CallResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Call"))
                  .build();
          }
        }
     }
     return getCallMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSendRawTransactionMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.SendRawTransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> METHOD_SEND_RAW_TRANSACTION = getSendRawTransactionMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.SendRawTransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> getSendRawTransactionMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.SendRawTransactionRequest,
      rpcpb.Rpc.SendTransactionResponse> getSendRawTransactionMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.SendRawTransactionRequest, rpcpb.Rpc.SendTransactionResponse> getSendRawTransactionMethod;
    if ((getSendRawTransactionMethod = ApiServiceGrpc.getSendRawTransactionMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSendRawTransactionMethod = ApiServiceGrpc.getSendRawTransactionMethod) == null) {
          ApiServiceGrpc.getSendRawTransactionMethod = getSendRawTransactionMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.SendRawTransactionRequest, rpcpb.Rpc.SendTransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "SendRawTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SendRawTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SendTransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("SendRawTransaction"))
                  .build();
          }
        }
     }
     return getSendRawTransactionMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetBlockByHashMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHashRequest,
      rpcpb.Rpc.BlockResponse> METHOD_GET_BLOCK_BY_HASH = getGetBlockByHashMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHashRequest,
      rpcpb.Rpc.BlockResponse> getGetBlockByHashMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHashRequest,
      rpcpb.Rpc.BlockResponse> getGetBlockByHashMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHashRequest, rpcpb.Rpc.BlockResponse> getGetBlockByHashMethod;
    if ((getGetBlockByHashMethod = ApiServiceGrpc.getGetBlockByHashMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetBlockByHashMethod = ApiServiceGrpc.getGetBlockByHashMethod) == null) {
          ApiServiceGrpc.getGetBlockByHashMethod = getGetBlockByHashMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.GetBlockByHashRequest, rpcpb.Rpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetBlockByHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetBlockByHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetBlockByHash"))
                  .build();
          }
        }
     }
     return getGetBlockByHashMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetBlockByHeightMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHeightRequest,
      rpcpb.Rpc.BlockResponse> METHOD_GET_BLOCK_BY_HEIGHT = getGetBlockByHeightMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHeightRequest,
      rpcpb.Rpc.BlockResponse> getGetBlockByHeightMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHeightRequest,
      rpcpb.Rpc.BlockResponse> getGetBlockByHeightMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.GetBlockByHeightRequest, rpcpb.Rpc.BlockResponse> getGetBlockByHeightMethod;
    if ((getGetBlockByHeightMethod = ApiServiceGrpc.getGetBlockByHeightMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetBlockByHeightMethod = ApiServiceGrpc.getGetBlockByHeightMethod) == null) {
          ApiServiceGrpc.getGetBlockByHeightMethod = getGetBlockByHeightMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.GetBlockByHeightRequest, rpcpb.Rpc.BlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetBlockByHeight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetBlockByHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.BlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetBlockByHeight"))
                  .build();
          }
        }
     }
     return getGetBlockByHeightMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetTransactionReceiptMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.GetTransactionByHashRequest,
      rpcpb.Rpc.TransactionResponse> METHOD_GET_TRANSACTION_RECEIPT = getGetTransactionReceiptMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.GetTransactionByHashRequest,
      rpcpb.Rpc.TransactionResponse> getGetTransactionReceiptMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.GetTransactionByHashRequest,
      rpcpb.Rpc.TransactionResponse> getGetTransactionReceiptMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.GetTransactionByHashRequest, rpcpb.Rpc.TransactionResponse> getGetTransactionReceiptMethod;
    if ((getGetTransactionReceiptMethod = ApiServiceGrpc.getGetTransactionReceiptMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetTransactionReceiptMethod = ApiServiceGrpc.getGetTransactionReceiptMethod) == null) {
          ApiServiceGrpc.getGetTransactionReceiptMethod = getGetTransactionReceiptMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.GetTransactionByHashRequest, rpcpb.Rpc.TransactionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetTransactionReceipt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetTransactionByHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.TransactionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetTransactionReceipt"))
                  .build();
          }
        }
     }
     return getGetTransactionReceiptMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSubscribeMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.SubscribeRequest,
      rpcpb.Rpc.SubscribeResponse> METHOD_SUBSCRIBE = getSubscribeMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.SubscribeRequest,
      rpcpb.Rpc.SubscribeResponse> getSubscribeMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.SubscribeRequest,
      rpcpb.Rpc.SubscribeResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.SubscribeRequest, rpcpb.Rpc.SubscribeResponse> getSubscribeMethod;
    if ((getSubscribeMethod = ApiServiceGrpc.getSubscribeMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getSubscribeMethod = ApiServiceGrpc.getSubscribeMethod) == null) {
          ApiServiceGrpc.getSubscribeMethod = getSubscribeMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.SubscribeRequest, rpcpb.Rpc.SubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.SubscribeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("Subscribe"))
                  .build();
          }
        }
     }
     return getSubscribeMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetGasPriceMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GasPriceResponse> METHOD_GET_GAS_PRICE = getGetGasPriceMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GasPriceResponse> getGetGasPriceMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest,
      rpcpb.Rpc.GasPriceResponse> getGetGasPriceMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.GasPriceResponse> getGetGasPriceMethod;
    if ((getGetGasPriceMethod = ApiServiceGrpc.getGetGasPriceMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetGasPriceMethod = ApiServiceGrpc.getGetGasPriceMethod) == null) {
          ApiServiceGrpc.getGetGasPriceMethod = getGetGasPriceMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.NonParamsRequest, rpcpb.Rpc.GasPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetGasPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.NonParamsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GasPriceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetGasPrice"))
                  .build();
          }
        }
     }
     return getGetGasPriceMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getEstimateGasMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.GasResponse> METHOD_ESTIMATE_GAS = getEstimateGasMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.GasResponse> getEstimateGasMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest,
      rpcpb.Rpc.GasResponse> getEstimateGasMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.GasResponse> getEstimateGasMethod;
    if ((getEstimateGasMethod = ApiServiceGrpc.getEstimateGasMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getEstimateGasMethod = ApiServiceGrpc.getEstimateGasMethod) == null) {
          ApiServiceGrpc.getEstimateGasMethod = getEstimateGasMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.TransactionRequest, rpcpb.Rpc.GasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "EstimateGas"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.TransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GasResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("EstimateGas"))
                  .build();
          }
        }
     }
     return getEstimateGasMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetGasUsedMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.GasResponse> METHOD_GET_GAS_USED = getGetGasUsedMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.GasResponse> getGetGasUsedMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.GasResponse> getGetGasUsedMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest, rpcpb.Rpc.GasResponse> getGetGasUsedMethod;
    if ((getGetGasUsedMethod = ApiServiceGrpc.getGetGasUsedMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetGasUsedMethod = ApiServiceGrpc.getGetGasUsedMethod) == null) {
          ApiServiceGrpc.getGetGasUsedMethod = getGetGasUsedMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.HashRequest, rpcpb.Rpc.GasResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetGasUsed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.HashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GasResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetGasUsed"))
                  .build();
          }
        }
     }
     return getGetGasUsedMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetEventsByHashMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.EventsResponse> METHOD_GET_EVENTS_BY_HASH = getGetEventsByHashMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.EventsResponse> getGetEventsByHashMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest,
      rpcpb.Rpc.EventsResponse> getGetEventsByHashMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.HashRequest, rpcpb.Rpc.EventsResponse> getGetEventsByHashMethod;
    if ((getGetEventsByHashMethod = ApiServiceGrpc.getGetEventsByHashMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetEventsByHashMethod = ApiServiceGrpc.getGetEventsByHashMethod) == null) {
          ApiServiceGrpc.getGetEventsByHashMethod = getGetEventsByHashMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.HashRequest, rpcpb.Rpc.EventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetEventsByHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.HashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.EventsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetEventsByHash"))
                  .build();
          }
        }
     }
     return getGetEventsByHashMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDynastyMethod()} instead. 
  public static final io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> METHOD_GET_DYNASTY = getGetDynastyMethod();

  private static volatile io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest,
      rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod() {
    io.grpc.MethodDescriptor<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetDynastyResponse> getGetDynastyMethod;
    if ((getGetDynastyMethod = ApiServiceGrpc.getGetDynastyMethod) == null) {
      synchronized (ApiServiceGrpc.class) {
        if ((getGetDynastyMethod = ApiServiceGrpc.getGetDynastyMethod) == null) {
          ApiServiceGrpc.getGetDynastyMethod = getGetDynastyMethod = 
              io.grpc.MethodDescriptor.<rpcpb.Rpc.ByBlockHeightRequest, rpcpb.Rpc.GetDynastyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "rpcpb.ApiService", "GetDynasty"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.ByBlockHeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcpb.Rpc.GetDynastyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ApiServiceMethodDescriptorSupplier("GetDynasty"))
                  .build();
          }
        }
     }
     return getGetDynastyMethod;
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
    public void getNebState(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetNebStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNebStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public void nodeInfo(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.NodeInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNodeInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void blockDump(rpcpb.Rpc.BlockDumpRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockDumpResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockDumpMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the latest irreversible block.
     * </pre>
     */
    public void latestIrreversibleBlock(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLatestIrreversibleBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public void accounts(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.AccountsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public void getAccountState(rpcpb.Rpc.GetAccountStateRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetAccountStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountStateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public void sendTransaction(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public void call(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.CallResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public void sendRawTransaction(rpcpb.Rpc.SendRawTransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendRawTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public void getBlockByHash(rpcpb.Rpc.GetBlockByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByHashMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public void getBlockByHeight(rpcpb.Rpc.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockByHeightMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public void getTransactionReceipt(rpcpb.Rpc.GetTransactionByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.TransactionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionReceiptMethod(), responseObserver);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public void subscribe(rpcpb.Rpc.SubscribeRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SubscribeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public void getGasPrice(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasPriceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGasPriceMethod(), responseObserver);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public void estimateGas(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEstimateGasMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public void getGasUsed(rpcpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGasUsedMethod(), responseObserver);
    }

    /**
     */
    public void getEventsByHash(rpcpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.EventsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEventsByHashMethod(), responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDynastyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNebStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.GetNebStateResponse>(
                  this, METHODID_GET_NEB_STATE)))
          .addMethod(
            getNodeInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.NodeInfoResponse>(
                  this, METHODID_NODE_INFO)))
          .addMethod(
            getBlockDumpMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.BlockDumpRequest,
                rpcpb.Rpc.BlockDumpResponse>(
                  this, METHODID_BLOCK_DUMP)))
          .addMethod(
            getLatestIrreversibleBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.BlockResponse>(
                  this, METHODID_LATEST_IRREVERSIBLE_BLOCK)))
          .addMethod(
            getAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.AccountsResponse>(
                  this, METHODID_ACCOUNTS)))
          .addMethod(
            getGetAccountStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.GetAccountStateRequest,
                rpcpb.Rpc.GetAccountStateResponse>(
                  this, METHODID_GET_ACCOUNT_STATE)))
          .addMethod(
            getSendTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.TransactionRequest,
                rpcpb.Rpc.SendTransactionResponse>(
                  this, METHODID_SEND_TRANSACTION)))
          .addMethod(
            getCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.TransactionRequest,
                rpcpb.Rpc.CallResponse>(
                  this, METHODID_CALL)))
          .addMethod(
            getSendRawTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.SendRawTransactionRequest,
                rpcpb.Rpc.SendTransactionResponse>(
                  this, METHODID_SEND_RAW_TRANSACTION)))
          .addMethod(
            getGetBlockByHashMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.GetBlockByHashRequest,
                rpcpb.Rpc.BlockResponse>(
                  this, METHODID_GET_BLOCK_BY_HASH)))
          .addMethod(
            getGetBlockByHeightMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.GetBlockByHeightRequest,
                rpcpb.Rpc.BlockResponse>(
                  this, METHODID_GET_BLOCK_BY_HEIGHT)))
          .addMethod(
            getGetTransactionReceiptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.GetTransactionByHashRequest,
                rpcpb.Rpc.TransactionResponse>(
                  this, METHODID_GET_TRANSACTION_RECEIPT)))
          .addMethod(
            getSubscribeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                rpcpb.Rpc.SubscribeRequest,
                rpcpb.Rpc.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getGetGasPriceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.NonParamsRequest,
                rpcpb.Rpc.GasPriceResponse>(
                  this, METHODID_GET_GAS_PRICE)))
          .addMethod(
            getEstimateGasMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.TransactionRequest,
                rpcpb.Rpc.GasResponse>(
                  this, METHODID_ESTIMATE_GAS)))
          .addMethod(
            getGetGasUsedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.HashRequest,
                rpcpb.Rpc.GasResponse>(
                  this, METHODID_GET_GAS_USED)))
          .addMethod(
            getGetEventsByHashMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.HashRequest,
                rpcpb.Rpc.EventsResponse>(
                  this, METHODID_GET_EVENTS_BY_HASH)))
          .addMethod(
            getGetDynastyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcpb.Rpc.ByBlockHeightRequest,
                rpcpb.Rpc.GetDynastyResponse>(
                  this, METHODID_GET_DYNASTY)))
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
    public void getNebState(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetNebStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNebStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public void nodeInfo(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.NodeInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNodeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public void blockDump(rpcpb.Rpc.BlockDumpRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockDumpResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockDumpMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the latest irreversible block.
     * </pre>
     */
    public void latestIrreversibleBlock(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLatestIrreversibleBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public void accounts(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.AccountsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public void getAccountState(rpcpb.Rpc.GetAccountStateRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetAccountStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public void sendTransaction(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public void call(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.CallResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public void sendRawTransaction(rpcpb.Rpc.SendRawTransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendRawTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public void getBlockByHash(rpcpb.Rpc.GetBlockByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public void getBlockByHeight(rpcpb.Rpc.GetBlockByHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public void getTransactionReceipt(rpcpb.Rpc.GetTransactionByHashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.TransactionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionReceiptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public void subscribe(rpcpb.Rpc.SubscribeRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.SubscribeResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public void getGasPrice(rpcpb.Rpc.NonParamsRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasPriceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGasPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public void estimateGas(rpcpb.Rpc.TransactionRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public void getGasUsed(rpcpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGasUsedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEventsByHash(rpcpb.Rpc.HashRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.EventsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEventsByHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDynasty(rpcpb.Rpc.ByBlockHeightRequest request,
        io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDynastyMethod(), getCallOptions()), request, responseObserver);
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
    public rpcpb.Rpc.GetNebStateResponse getNebState(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetNebStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public rpcpb.Rpc.NodeInfoResponse nodeInfo(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getNodeInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public rpcpb.Rpc.BlockDumpResponse blockDump(rpcpb.Rpc.BlockDumpRequest request) {
      return blockingUnaryCall(
          getChannel(), getBlockDumpMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the latest irreversible block.
     * </pre>
     */
    public rpcpb.Rpc.BlockResponse latestIrreversibleBlock(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLatestIrreversibleBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public rpcpb.Rpc.AccountsResponse accounts(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public rpcpb.Rpc.GetAccountStateResponse getAccountState(rpcpb.Rpc.GetAccountStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountStateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public rpcpb.Rpc.SendTransactionResponse sendTransaction(rpcpb.Rpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public rpcpb.Rpc.CallResponse call(rpcpb.Rpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public rpcpb.Rpc.SendTransactionResponse sendRawTransaction(rpcpb.Rpc.SendRawTransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendRawTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public rpcpb.Rpc.BlockResponse getBlockByHash(rpcpb.Rpc.GetBlockByHashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public rpcpb.Rpc.BlockResponse getBlockByHeight(rpcpb.Rpc.GetBlockByHeightRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockByHeightMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public rpcpb.Rpc.TransactionResponse getTransactionReceipt(rpcpb.Rpc.GetTransactionByHashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionReceiptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Subscribe message
     * </pre>
     */
    public java.util.Iterator<rpcpb.Rpc.SubscribeResponse> subscribe(
        rpcpb.Rpc.SubscribeRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public rpcpb.Rpc.GasPriceResponse getGasPrice(rpcpb.Rpc.NonParamsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetGasPriceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public rpcpb.Rpc.GasResponse estimateGas(rpcpb.Rpc.TransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getEstimateGasMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public rpcpb.Rpc.GasResponse getGasUsed(rpcpb.Rpc.HashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetGasUsedMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.EventsResponse getEventsByHash(rpcpb.Rpc.HashRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetEventsByHashMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcpb.Rpc.GetDynastyResponse getDynasty(rpcpb.Rpc.ByBlockHeightRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDynastyMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetNebStateResponse> getNebState(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNebStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the p2p node info.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.NodeInfoResponse> nodeInfo(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNodeInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the dump info of blockchain.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.BlockDumpResponse> blockDump(
        rpcpb.Rpc.BlockDumpRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockDumpMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the latest irreversible block.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.BlockResponse> latestIrreversibleBlock(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLatestIrreversibleBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Accounts return account list.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.AccountsResponse> accounts(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAccountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Return the state of the account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetAccountStateResponse> getAccountState(
        rpcpb.Rpc.GetAccountStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountStateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Verify, sign, and send the transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.SendTransactionResponse> sendTransaction(
        rpcpb.Rpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Call smart contract.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.CallResponse> call(
        rpcpb.Rpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit the signed transaction.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.SendTransactionResponse> sendRawTransaction(
        rpcpb.Rpc.SendRawTransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendRawTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get block info by the block hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.BlockResponse> getBlockByHash(
        rpcpb.Rpc.GetBlockByHashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get block info by the block height.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.BlockResponse> getBlockByHeight(
        rpcpb.Rpc.GetBlockByHeightRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get transactionReceipt info by tansaction hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.TransactionResponse> getTransactionReceipt(
        rpcpb.Rpc.GetTransactionByHashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionReceiptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get GasPrice
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GasPriceResponse> getGasPrice(
        rpcpb.Rpc.NonParamsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGasPriceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * EstimateGas
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GasResponse> estimateGas(
        rpcpb.Rpc.TransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEstimateGasMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get GasUsed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GasResponse> getGasUsed(
        rpcpb.Rpc.HashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGasUsedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.EventsResponse> getEventsByHash(
        rpcpb.Rpc.HashRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEventsByHashMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcpb.Rpc.GetDynastyResponse> getDynasty(
        rpcpb.Rpc.ByBlockHeightRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDynastyMethod(), getCallOptions()), request);
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
  private static final int METHODID_GET_DYNASTY = 17;

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
          serviceImpl.getNebState((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetNebStateResponse>) responseObserver);
          break;
        case METHODID_NODE_INFO:
          serviceImpl.nodeInfo((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.NodeInfoResponse>) responseObserver);
          break;
        case METHODID_BLOCK_DUMP:
          serviceImpl.blockDump((rpcpb.Rpc.BlockDumpRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockDumpResponse>) responseObserver);
          break;
        case METHODID_LATEST_IRREVERSIBLE_BLOCK:
          serviceImpl.latestIrreversibleBlock((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse>) responseObserver);
          break;
        case METHODID_ACCOUNTS:
          serviceImpl.accounts((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.AccountsResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_STATE:
          serviceImpl.getAccountState((rpcpb.Rpc.GetAccountStateRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetAccountStateResponse>) responseObserver);
          break;
        case METHODID_SEND_TRANSACTION:
          serviceImpl.sendTransaction((rpcpb.Rpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse>) responseObserver);
          break;
        case METHODID_CALL:
          serviceImpl.call((rpcpb.Rpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.CallResponse>) responseObserver);
          break;
        case METHODID_SEND_RAW_TRANSACTION:
          serviceImpl.sendRawTransaction((rpcpb.Rpc.SendRawTransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.SendTransactionResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_HASH:
          serviceImpl.getBlockByHash((rpcpb.Rpc.GetBlockByHashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse>) responseObserver);
          break;
        case METHODID_GET_BLOCK_BY_HEIGHT:
          serviceImpl.getBlockByHeight((rpcpb.Rpc.GetBlockByHeightRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.BlockResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_RECEIPT:
          serviceImpl.getTransactionReceipt((rpcpb.Rpc.GetTransactionByHashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.TransactionResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((rpcpb.Rpc.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.SubscribeResponse>) responseObserver);
          break;
        case METHODID_GET_GAS_PRICE:
          serviceImpl.getGasPrice((rpcpb.Rpc.NonParamsRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GasPriceResponse>) responseObserver);
          break;
        case METHODID_ESTIMATE_GAS:
          serviceImpl.estimateGas((rpcpb.Rpc.TransactionRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse>) responseObserver);
          break;
        case METHODID_GET_GAS_USED:
          serviceImpl.getGasUsed((rpcpb.Rpc.HashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GasResponse>) responseObserver);
          break;
        case METHODID_GET_EVENTS_BY_HASH:
          serviceImpl.getEventsByHash((rpcpb.Rpc.HashRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.EventsResponse>) responseObserver);
          break;
        case METHODID_GET_DYNASTY:
          serviceImpl.getDynasty((rpcpb.Rpc.ByBlockHeightRequest) request,
              (io.grpc.stub.StreamObserver<rpcpb.Rpc.GetDynastyResponse>) responseObserver);
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
      return rpcpb.Rpc.getDescriptor();
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
              .addMethod(getGetDynastyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
