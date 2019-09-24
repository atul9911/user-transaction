package pojo;

public class ApiResponse<T> {
  private String responseMessage;
  private int responseCode;
  private String requestId;
  private T payload;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public ApiResponse(int responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public T getPayload() {
    return payload;
  }

  public ApiResponse<T> setPayload(T payload) {
    this.payload = payload;
    return this;
  }

}
