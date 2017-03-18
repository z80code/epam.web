package by.gsu.epamlab.models;

import by.gsu.epamlab.enums.RequestStatus;

public class ViewModel {
 /* a returned object have next structure:
 @fields: status  = "error"|"ok",
 message = "text of the message if an error detected",
 data    = {}
 */
    private RequestStatus status;
    private String message;
    private Object data;

  public ViewModel() {
  }

  public ViewModel(RequestStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

  public RequestStatus getStatus() {
    return status;
  }

  public void setStatus(RequestStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
