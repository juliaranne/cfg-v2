import { useEffect, useState } from 'react';
import CloseIcon from '@material-ui/icons/Close';

// const AlertMessageWrapper = ({message}) => {

// }

const AlertMessage = ({message}) => {
    const [show, setShow] = useState(false);

    const dismissAlert = () => {
        setShow(false);
    }

    useEffect(() => {
        setTimeout(() => setShow(true), 100);
        setTimeout(() => dismissAlert(), 4000);
    },[])

    return (
        <div className={`sentiment ${show ? 'active' : ''}`} aria-live="polite" id="sentimentMessage">
            <div className="sentiment__wrapper">
                <p className="sentiment__message">{message}</p>
                <button onClick={dismissAlert} type="button"><CloseIcon></CloseIcon><span className='sr-only'>Close alert message</span></button>
            </div>
        </div>
    )
}

export default AlertMessage;