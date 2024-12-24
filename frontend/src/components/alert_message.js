import { useEffect, useState, useCallback, useRef } from 'react';
import CloseIcon from '@material-ui/icons/Close';

const AlertMessage = ({message, handleUnmount}) => {
    const [show, setShow] = useState(false);
    const closeBtnRef = useRef();
    const alertElRef = useRef();

    const dismissAlert = useCallback(() => {
        setShow(false);
        alertElRef?.current.addEventListener('transitionend', () => {
            handleUnmount();
        })
    }, [handleUnmount])

    useEffect(() => {
        closeBtnRef.current.focus();
        setShow(true);
        setTimeout(() => dismissAlert(), 4000);
    },[dismissAlert])

    return (
        <div ref={alertElRef} className={`sentiment ${show ? 'active' : ''}`} aria-live="polite" id="sentimentMessage">
            <div className="sentiment__wrapper">
                <p className="sentiment__message">{message}</p>
                <button ref={closeBtnRef} onClick={dismissAlert} type="button"><CloseIcon></CloseIcon><span className='sr-only'>Close alert message</span></button>
            </div>
        </div>
    )
}

export default AlertMessage;
