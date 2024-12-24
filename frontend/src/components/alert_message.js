import { useEffect, useState, useCallback, useRef } from 'react';
import CloseIcon from '@material-ui/icons/Close';

const AlertMessage = ({message, unmount}) => {
    const [show, setShow] = useState(false);
    const closeBtnRef = useRef();
    const alertEl = useRef();

    const dismissAlert = () => {
        setShow(false);
        unmount();
    }

    const handleAnimationEnd = useCallback(() => {
        alertEl.current.addEventListener("animationend", () => {
            setShow(false);
            unmount()
        });
    },[unmount])

    useEffect(() => {
        closeBtnRef.current.focus();
        setTimeout(() => setShow(true), 1);
        handleAnimationEnd();
    },[handleAnimationEnd])

    return (
        <div ref={alertEl} className={`sentiment ${show ? 'active' : ''}`} aria-live="polite" id="sentimentMessage">
            <div className="sentiment__wrapper">
                <p className="sentiment__message">{message}</p>
                <button ref={closeBtnRef} onClick={dismissAlert} type="button"><CloseIcon></CloseIcon><span className='sr-only'>Close alert message</span></button>
            </div>
        </div>
    )
}

export default AlertMessage;