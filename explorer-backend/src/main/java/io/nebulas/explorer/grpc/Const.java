package io.nebulas.explorer.grpc;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
public class Const {
    // below tx

    // TopicSendTransaction the topic of send a transaction.
    public static final String TopicSendTransaction = "chain.sendTransaction";

    // TopicDeploySmartContract the topic of deploy a smart contract.
    public static final String TopicDeploySmartContract = "chain.deployContract";

    // TopicCallSmartContract the topic of call a smart contract.
    public static final String TopicCallSmartContract = "chain.callContract";

    // TopicDelegate the topic of delegate.
    public static final String TopicDelegate = "chain.delegate";

    // TopicCandidate the topic of candidate.
    public static final String TopicCandidate = "chain.candidate";

    // mutable do not persist
    public static final String TopicPendingTransaction = "chain.pendingTransaction";

    // above tx

    // below block

    // TopicLinkBlock the topic of link a block.
    public static final String TopicLinkBlock = "chain.linkBlock";

    // TopicLibBlock the topic of latest irreversible block.
    public static final String TopicLibBlock = "chain.latestIrreversibleBlock";

    // above block


    // mutable ignore
    // TopicExecuteTxFailed the topic of execute a transaction failed.
    @Deprecated
    public static final String TopicExecuteTxFailed = "chain.executeTxFailed";

    // mutable ignore
    // TopicExecuteTxSuccess the topic of execute a transaction success.
    @Deprecated
    public static final String TopicExecuteTxSuccess = "chain.executeTxSuccess";

}
