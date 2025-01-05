import { useEffect, useState, useCallback, useRef } from "react";
import CloseIcon from "@mui/icons-material/Close";

const AlertMessage = ({ message, handleClose }) => {
  const [show, setShow] = useState(false);
  const closeBtnRef = useRef();
  const alertElRef = useRef();

  const dismissAlert = useCallback(() => {
    setShow(false);
    alertElRef?.current?.addEventListener("transitionend", () => {
      handleClose();
    });
  }, [handleClose]);

  useEffect(() => {
    setTimeout(() => setShow(true), 100);
    setTimeout(() => dismissAlert(), 4000);
  }, [dismissAlert]);

  return (
    <div
      ref={alertElRef}
      className={`sentiment ${show ? "active" : ""}`}
      id="sentimentMessage"
    >
      <div className="sentiment__wrapper">
        <p className="sentiment__message">{message}</p>
        <button ref={closeBtnRef} onClick={dismissAlert} type="button">
          <CloseIcon></CloseIcon>
          <span className="sr-only">Close alert message</span>
        </button>
      </div>
    </div>
  );
};

export default AlertMessage;
