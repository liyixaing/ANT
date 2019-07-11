package com.example.administrator.mode.ethutlis;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class ANTToken extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_WITHDRAWLOCKRECORDALLPROFIT = "WithDrawLockRecordAllProfit";

    public static final String FUNC_API_GETENABLEWITHDRAWPOSPROFIT = "API_GetEnableWithDrawPosProfit";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_GETPOSOUTLISTS = "GetPosoutLists";

    public static final String FUNC_POSOUTWRITERREWARD = "posoutWriterReward";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_GETADMINLIST = "GetAdminList";

    public static final String FUNC_EVERDAYPOSTOKENAMOUNT = "everDayPosTokenAmount";

    public static final String FUNC_GETCURRENTPOSSUM = "GetCurrentPosSum";

    public static final String FUNC_API_SENDLOCKBALANCETO = "API_SendLockBalanceTo";

    public static final String FUNC_GETLOCKRECORDS = "GetLockRecords";

    public static final String FUNC_POSTOUTWRITERREWARD = "postoutWriterReward";

    public static final String FUNC_PERMINERAMOUNT = "perMinerAmount";

    public static final String FUNC_WITHDRAWPOSPROFIT = "WithDrawPosProfit";

    public static final String FUNC_DESPOITTOPOS = "DespoitToPos";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_REMOVEADMIN = "RemoveAdmin";

    public static final String FUNC_API_SETENABLEWITHDRAWPOSPROFIT = "API_SetEnableWithDrawPosProfit";

    public static final String FUNC_AIRDROPADDRESS = "airdropAddress";

    public static final String FUNC_RESCISSIONPOSAT = "RescissionPosAt";

    public static final String FUNC_API_SETUNLOCKAMOUNTENABLE = "API_SetUnlockAmountEnable";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_API_SETPOSOUTWRITEREWARD = "API_SetPosoutWriteReward";

    public static final String FUNC_GETPOSRECORDS = "GetPosRecords";

    public static final String FUNC_JOINPOSMINAMOUNT = "joinPosMinAmount";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_ADDADMIN = "AddAdmin";

    public static final String FUNC_WITHDRAWLOCKRECORDPROFIT = "WithDrawLockRecordProFit";

    public static final String FUNC_WITHDRAWPOSALLPROFIT = "WithDrawPosAllProfit";

    public static final String FUNC_MAXREMEBERPOSRECORD = "maxRemeberPosRecord";

    public static final String FUNC_API_SETEVERDAYPOSMAXAMOUNT = "API_SetEverDayPosMaxAmount";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_RESCISSIONPOSALL = "RescissionPosAll";

    public static final String FUNC_STARTUNLOCKDATATIME = "startUnlockDataTime";

    public static final Event ONCREATEPOSRECORD_EVENT = new Event("OnCreatePosRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ONRESCISSIONPOSRECORD_EVENT = new Event("OnRescissionPosRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event ONRESCISSIONPOSRECORDALL_EVENT = new Event("OnRescissionPosRecordAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event ONWITHDRAWPOSRECORDPOFIT_EVENT = new Event("OnWithdrawPosRecordPofit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event ONWITHDRAWPOSRECORDPOFITALL_EVENT = new Event("OnWithdrawPosRecordPofitAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event ONSENDLOCKAMOUNT_EVENT = new Event("OnSendLockAmount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ONWITHDRAWLOCKRECORD_EVENT = new Event("OnWithdrawLockRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ONWITHDRAWLOCKRECORDALL_EVENT = new Event("OnWithdrawLockRecordAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ANTToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ANTToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ANTToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ANTToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> WithDrawLockRecordAllProfit() {
        final Function function = new Function(
                FUNC_WITHDRAWLOCKRECORDALLPROFIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> API_GetEnableWithDrawPosProfit() {
        final Function function = new Function(FUNC_API_GETENABLEWITHDRAWPOSPROFIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>>> GetPosoutLists() {
        final Function function = new Function(FUNC_GETPOSOUTLISTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> posoutWriterReward() {
        final Function function = new Function(FUNC_POSOUTWRITERREWARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> GetAdminList() {
        final Function function = new Function(FUNC_GETADMINLIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> everDayPosTokenAmount() {
        final Function function = new Function(FUNC_EVERDAYPOSTOKENAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> GetCurrentPosSum() {
        final Function function = new Function(FUNC_GETCURRENTPOSSUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> API_SendLockBalanceTo(String _to, BigInteger lockAmountTotal, BigInteger lockDays) {
        final Function function = new Function(
                FUNC_API_SENDLOCKBALANCETO, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(lockAmountTotal),
                new Uint16(lockDays)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> GetLockRecords() {
        final Function function = new Function(FUNC_GETLOCKRECORDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint16>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple6<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple6<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple6<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()), 
                                convertToNative((List<Uint16>) results.get(4).getValue()), 
                                convertToNative((List<Uint256>) results.get(5).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> postoutWriterReward() {
        final Function function = new Function(FUNC_POSTOUTWRITERREWARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> perMinerAmount() {
        final Function function = new Function(FUNC_PERMINERAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> WithDrawPosProfit(BigInteger posRecordIndex) {
        final Function function = new Function(
                FUNC_WITHDRAWPOSPROFIT, 
                Arrays.<Type>asList(new Uint256(posRecordIndex)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> DespoitToPos(BigInteger amount) {
        final Function function = new Function(
                FUNC_DESPOITTOPOS, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> RemoveAdmin(String admin) {
        final Function function = new Function(
                FUNC_REMOVEADMIN, 
                Arrays.<Type>asList(new Address(admin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> API_SetEnableWithDrawPosProfit(Boolean state) {
        final Function function = new Function(
                FUNC_API_SETENABLEWITHDRAWPOSPROFIT, 
                Arrays.<Type>asList(new Bool(state)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> airdropAddress() {
        final Function function = new Function(FUNC_AIRDROPADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> RescissionPosAt(BigInteger posRecordIndex) {
        final Function function = new Function(
                FUNC_RESCISSIONPOSAT, 
                Arrays.<Type>asList(new Uint256(posRecordIndex)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> API_SetUnlockAmountEnable(BigInteger startTime) {
        final Function function = new Function(
                FUNC_API_SETUNLOCKAMOUNTENABLE, 
                Arrays.<Type>asList(new Uint256(startTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> API_SetPosoutWriteReward(BigInteger reward) {
        final Function function = new Function(
                FUNC_API_SETPOSOUTWRITEREWARD, 
                Arrays.<Type>asList(new Uint256(reward)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> GetPosRecords() {
        final Function function = new Function(FUNC_GETPOSRECORDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple5<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple5<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple5<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()), 
                                convertToNative((List<Uint256>) results.get(4).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> joinPosMinAmount() {
        final Function function = new Function(FUNC_JOINPOSMINAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> AddAdmin(String admin) {
        final Function function = new Function(
                FUNC_ADDADMIN, 
                Arrays.<Type>asList(new Address(admin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> WithDrawLockRecordProFit(BigInteger rid) {
        final Function function = new Function(
                FUNC_WITHDRAWLOCKRECORDPROFIT, 
                Arrays.<Type>asList(new Uint256(rid)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> WithDrawPosAllProfit() {
        final Function function = new Function(
                FUNC_WITHDRAWPOSALLPROFIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> maxRemeberPosRecord() {
        final Function function = new Function(FUNC_MAXREMEBERPOSRECORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> API_SetEverDayPosMaxAmount(BigInteger maxAmount) {
        final Function function = new Function(
                FUNC_API_SETEVERDAYPOSMAXAMOUNT, 
                Arrays.<Type>asList(new Uint256(maxAmount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new Address(_owner),
                new Address(_spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> RescissionPosAll() {
        final Function function = new Function(
                FUNC_RESCISSIONPOSALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> startUnlockDataTime() {
        final Function function = new Function(FUNC_STARTUNLOCKDATATIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<OnCreatePosRecordEventResponse> getOnCreatePosRecordEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONCREATEPOSRECORD_EVENT, transactionReceipt);
        ArrayList<OnCreatePosRecordEventResponse> responses = new ArrayList<OnCreatePosRecordEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnCreatePosRecordEventResponse typedResponse = new OnCreatePosRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.posAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnCreatePosRecordEventResponse> onCreatePosRecordEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnCreatePosRecordEventResponse>() {
            @Override
            public OnCreatePosRecordEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONCREATEPOSRECORD_EVENT, log);
                OnCreatePosRecordEventResponse typedResponse = new OnCreatePosRecordEventResponse();
                typedResponse.log = log;
                typedResponse.posAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnCreatePosRecordEventResponse> onCreatePosRecordEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONCREATEPOSRECORD_EVENT));
        return onCreatePosRecordEventFlowable(filter);
    }

    public List<OnRescissionPosRecordEventResponse> getOnRescissionPosRecordEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONRESCISSIONPOSRECORD_EVENT, transactionReceipt);
        ArrayList<OnRescissionPosRecordEventResponse> responses = new ArrayList<OnRescissionPosRecordEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnRescissionPosRecordEventResponse typedResponse = new OnRescissionPosRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.posAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.recordCreateTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lastWithDrawTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.posProfit = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnRescissionPosRecordEventResponse> onRescissionPosRecordEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnRescissionPosRecordEventResponse>() {
            @Override
            public OnRescissionPosRecordEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONRESCISSIONPOSRECORD_EVENT, log);
                OnRescissionPosRecordEventResponse typedResponse = new OnRescissionPosRecordEventResponse();
                typedResponse.log = log;
                typedResponse.posAmount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.recordCreateTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.lastWithDrawTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.posProfit = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnRescissionPosRecordEventResponse> onRescissionPosRecordEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONRESCISSIONPOSRECORD_EVENT));
        return onRescissionPosRecordEventFlowable(filter);
    }

    public List<OnRescissionPosRecordAllEventResponse> getOnRescissionPosRecordAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONRESCISSIONPOSRECORDALL_EVENT, transactionReceipt);
        ArrayList<OnRescissionPosRecordAllEventResponse> responses = new ArrayList<OnRescissionPosRecordAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnRescissionPosRecordAllEventResponse typedResponse = new OnRescissionPosRecordAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnRescissionPosRecordAllEventResponse> onRescissionPosRecordAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnRescissionPosRecordAllEventResponse>() {
            @Override
            public OnRescissionPosRecordAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONRESCISSIONPOSRECORDALL_EVENT, log);
                OnRescissionPosRecordAllEventResponse typedResponse = new OnRescissionPosRecordAllEventResponse();
                typedResponse.log = log;
                typedResponse.amountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnRescissionPosRecordAllEventResponse> onRescissionPosRecordAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONRESCISSIONPOSRECORDALL_EVENT));
        return onRescissionPosRecordAllEventFlowable(filter);
    }

    public List<OnWithdrawPosRecordPofitEventResponse> getOnWithdrawPosRecordPofitEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONWITHDRAWPOSRECORDPOFIT_EVENT, transactionReceipt);
        ArrayList<OnWithdrawPosRecordPofitEventResponse> responses = new ArrayList<OnWithdrawPosRecordPofitEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnWithdrawPosRecordPofitEventResponse typedResponse = new OnWithdrawPosRecordPofitEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.depositTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lastWithDrawTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.profit = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnWithdrawPosRecordPofitEventResponse> onWithdrawPosRecordPofitEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnWithdrawPosRecordPofitEventResponse>() {
            @Override
            public OnWithdrawPosRecordPofitEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONWITHDRAWPOSRECORDPOFIT_EVENT, log);
                OnWithdrawPosRecordPofitEventResponse typedResponse = new OnWithdrawPosRecordPofitEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.depositTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.lastWithDrawTime = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.profit = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnWithdrawPosRecordPofitEventResponse> onWithdrawPosRecordPofitEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONWITHDRAWPOSRECORDPOFIT_EVENT));
        return onWithdrawPosRecordPofitEventFlowable(filter);
    }

    public List<OnWithdrawPosRecordPofitAllEventResponse> getOnWithdrawPosRecordPofitAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONWITHDRAWPOSRECORDPOFITALL_EVENT, transactionReceipt);
        ArrayList<OnWithdrawPosRecordPofitAllEventResponse> responses = new ArrayList<OnWithdrawPosRecordPofitAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnWithdrawPosRecordPofitAllEventResponse typedResponse = new OnWithdrawPosRecordPofitAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnWithdrawPosRecordPofitAllEventResponse> onWithdrawPosRecordPofitAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnWithdrawPosRecordPofitAllEventResponse>() {
            @Override
            public OnWithdrawPosRecordPofitAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONWITHDRAWPOSRECORDPOFITALL_EVENT, log);
                OnWithdrawPosRecordPofitAllEventResponse typedResponse = new OnWithdrawPosRecordPofitAllEventResponse();
                typedResponse.log = log;
                typedResponse.amountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sendedPosProfitToken = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnWithdrawPosRecordPofitAllEventResponse> onWithdrawPosRecordPofitAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONWITHDRAWPOSRECORDPOFITALL_EVENT));
        return onWithdrawPosRecordPofitAllEventFlowable(filter);
    }

    public List<OnSendLockAmountEventResponse> getOnSendLockAmountEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONSENDLOCKAMOUNT_EVENT, transactionReceipt);
        ArrayList<OnSendLockAmountEventResponse> responses = new ArrayList<OnSendLockAmountEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnSendLockAmountEventResponse typedResponse = new OnSendLockAmountEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lockDays = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnSendLockAmountEventResponse> onSendLockAmountEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnSendLockAmountEventResponse>() {
            @Override
            public OnSendLockAmountEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONSENDLOCKAMOUNT_EVENT, log);
                OnSendLockAmountEventResponse typedResponse = new OnSendLockAmountEventResponse();
                typedResponse.log = log;
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.lockDays = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnSendLockAmountEventResponse> onSendLockAmountEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONSENDLOCKAMOUNT_EVENT));
        return onSendLockAmountEventFlowable(filter);
    }

    public List<OnWithdrawLockRecordEventResponse> getOnWithdrawLockRecordEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONWITHDRAWLOCKRECORD_EVENT, transactionReceipt);
        ArrayList<OnWithdrawLockRecordEventResponse> responses = new ArrayList<OnWithdrawLockRecordEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnWithdrawLockRecordEventResponse typedResponse = new OnWithdrawLockRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.profit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.totalAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.withdrawAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.lastWithdrawTime = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.lockDays = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.createTime = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnWithdrawLockRecordEventResponse> onWithdrawLockRecordEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnWithdrawLockRecordEventResponse>() {
            @Override
            public OnWithdrawLockRecordEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONWITHDRAWLOCKRECORD_EVENT, log);
                OnWithdrawLockRecordEventResponse typedResponse = new OnWithdrawLockRecordEventResponse();
                typedResponse.log = log;
                typedResponse.profit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.totalAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.withdrawAmount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.lastWithdrawTime = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.lockDays = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.createTime = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnWithdrawLockRecordEventResponse> onWithdrawLockRecordEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONWITHDRAWLOCKRECORD_EVENT));
        return onWithdrawLockRecordEventFlowable(filter);
    }

    public List<OnWithdrawLockRecordAllEventResponse> getOnWithdrawLockRecordAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ONWITHDRAWLOCKRECORDALL_EVENT, transactionReceipt);
        ArrayList<OnWithdrawLockRecordAllEventResponse> responses = new ArrayList<OnWithdrawLockRecordAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OnWithdrawLockRecordAllEventResponse typedResponse = new OnWithdrawLockRecordAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.totalAmountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OnWithdrawLockRecordAllEventResponse> onWithdrawLockRecordAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OnWithdrawLockRecordAllEventResponse>() {
            @Override
            public OnWithdrawLockRecordAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(ONWITHDRAWLOCKRECORDALL_EVENT, log);
                OnWithdrawLockRecordAllEventResponse typedResponse = new OnWithdrawLockRecordAllEventResponse();
                typedResponse.log = log;
                typedResponse.totalAmountSum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.profitSum = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OnWithdrawLockRecordAllEventResponse> onWithdrawLockRecordAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONWITHDRAWLOCKRECORDALL_EVENT));
        return onWithdrawLockRecordAllEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    @Deprecated
    public static ANTToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ANTToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ANTToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ANTToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ANTToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ANTToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ANTToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ANTToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class OnCreatePosRecordEventResponse {
        public Log log;

        public BigInteger posAmount;
    }

    public static class OnRescissionPosRecordEventResponse {
        public Log log;

        public BigInteger posAmount;

        public BigInteger recordCreateTime;

        public BigInteger lastWithDrawTime;

        public BigInteger posProfit;

        public Boolean sendedPosProfitToken;
    }

    public static class OnRescissionPosRecordAllEventResponse {
        public Log log;

        public BigInteger amountSum;

        public BigInteger profitSum;

        public Boolean sendedPosProfitToken;
    }

    public static class OnWithdrawPosRecordPofitEventResponse {
        public Log log;

        public BigInteger amount;

        public BigInteger depositTime;

        public BigInteger lastWithDrawTime;

        public BigInteger profit;

        public Boolean sendedPosProfitToken;
    }

    public static class OnWithdrawPosRecordPofitAllEventResponse {
        public Log log;

        public BigInteger amountSum;

        public BigInteger profitSum;

        public Boolean sendedPosProfitToken;
    }

    public static class OnSendLockAmountEventResponse {
        public Log log;

        public String to;

        public BigInteger amount;

        public BigInteger lockDays;
    }

    public static class OnWithdrawLockRecordEventResponse {
        public Log log;

        public BigInteger profit;

        public BigInteger totalAmount;

        public BigInteger withdrawAmount;

        public BigInteger lastWithdrawTime;

        public BigInteger lockDays;

        public BigInteger createTime;
    }

    public static class OnWithdrawLockRecordAllEventResponse {
        public Log log;

        public BigInteger totalAmountSum;

        public BigInteger profitSum;
    }

    public static class TransferEventResponse {
        public Log log;

        public String _from;

        public String _to;

        public BigInteger _value;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String _owner;

        public String _spender;

        public BigInteger _value;
    }
}
