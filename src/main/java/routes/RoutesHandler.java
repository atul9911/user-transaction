package routes;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import model.User;
import model.Wallet;
import pojo.ApiResponse;
import pojo.TransactionPojo;
import pojo.UserPojo;
import service.BaseServiceRegistry;
import service.TransactionService;
import service.UserService;
import service.WalletService;
import utils.CommonUtil;
import utils.NullOrEmptyCheckerUtil;

class RoutesHandler {

	private static final UserService userService;

	private static final TransactionService transactionService;

	private static final WalletService walletService;

	static {
		userService = (UserService) BaseServiceRegistry.getService("user");
		transactionService = (TransactionService) BaseServiceRegistry.getService("transaction");
		walletService = (WalletService) BaseServiceRegistry.getService("wallet");
	}

	public static void createUserHandler(HttpServerExchange httpServerExchange) throws Exception {
		try {
			httpServerExchange.startBlocking();
			UserPojo userPojo = CommonUtil.getClassObjectFromStream(
					IOUtils.toString(httpServerExchange.getInputStream(), StandardCharsets.UTF_8.name()),
					UserPojo.class);
			Integer id = userService.addUser(userPojo);
			Map<String, Integer> responseMap = new HashMap<>();
			responseMap.put("Id", id);
			httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(responseMap));
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}
	}

	public static void validateUserHandler(HttpServerExchange exchange) throws Exception {
		try {
			String userId = exchange.getQueryParameters().get("id").getFirst();
			if (NullOrEmptyCheckerUtil.isNullOrEmpty(userId)) {
				ApiResponse<?> apiResponse = new ApiResponse<Void>(400);
				apiResponse.setResponseMessage("User Id missing from request");
				exchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
			}
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			exchange.getResponseSender().send("{res:\"Hello World\"}");
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			exchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}
	}

	public static void checkUserBalance(HttpServerExchange httpServerExchange) throws Exception {
		try {
			httpServerExchange.startBlocking();
			String walletId = httpServerExchange.getQueryParameters().get("id").getFirst();
			if (NullOrEmptyCheckerUtil.isNullOrEmpty(walletId)) {
				ApiResponse<?> apiResponse = new ApiResponse<>(400);
				apiResponse.setResponseMessage("Invalid Wallet");
				httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
			}
			Wallet wallet = walletService.validateWallet(Integer.valueOf(walletId));
			ApiResponse<Wallet> apiResponse = new ApiResponse<>(200);
			apiResponse.setPayload(wallet);
			httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}
	}

	public static void initiateTransaction(HttpServerExchange httpServerExchange) throws Exception {
		try {
			httpServerExchange.startBlocking();
			TransactionPojo transactionPojo = CommonUtil.getClassObjectFromStream(
					IOUtils.toString(httpServerExchange.getInputStream(), StandardCharsets.UTF_8.name()),
					TransactionPojo.class);
			if (NullOrEmptyCheckerUtil.isNullOrEmpty(transactionPojo)) {
				ApiResponse<?> apiResponse = new ApiResponse<>(400);
				apiResponse.setResponseMessage("Invalid Transaction");
				httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
			}
			Integer id = transactionService.initiateTransaction(transactionPojo);
			Map<String, Integer> responseMap = new HashMap<>();
			responseMap.put("Id", id);
			httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(responseMap));
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}
	}

	public static void makeTransaction(HttpServerExchange httpServerExchange) throws Exception {
		try {
			httpServerExchange.startBlocking();
			String transactionId = httpServerExchange.getQueryParameters().get("id").getFirst();
			TransactionPojo transactionPojo = transactionService.doTransaction(Integer.valueOf(transactionId));
			ApiResponse<TransactionPojo> apiResponse = new ApiResponse<>(200);
			apiResponse.setPayload(transactionPojo);
			apiResponse.setResponseMessage("Transaction Successful");
			httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}

	}

	public static void createWallet(HttpServerExchange httpServerExchange) throws Exception {
		try {
			httpServerExchange.startBlocking();
			String userId = httpServerExchange.getQueryParameters().get("userId").getFirst();
			User user = new User();
			user.setId(Integer.valueOf(userId));
			Integer id = walletService.addWallet(user);
			Map<String, Integer> responseMap = new HashMap<>();
			responseMap.put("Id", id);
			httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(responseMap));
		} catch (Exception exc) {
			ApiResponse<?> apiResponse = new ApiResponse<>(400);
			apiResponse.setResponseMessage(ExceptionUtils.getMessage(exc));
			httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));
		}
	}

	public static void setFailureResponse(HttpServerExchange httpServerExchange) throws Exception {
		ApiResponse<?> apiResponse = new ApiResponse<>(500);
		apiResponse.setResponseMessage("Internal Server Error");
		httpServerExchange.getResponseSender().send(CommonUtil.objectMapper.writeValueAsString(apiResponse));

	}

}
