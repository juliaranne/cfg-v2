import CloseIcon from '@material-ui/icons/Close';

const AlertMessage = ({message}) => {
    return (
        <div className={`sentiment ${message ? 'active' : ''}`} aria-live="polite" id="sentimentMessage">
            <div className="sentiment__wrapper">
                {message && <p className="sentiment__message">{message}</p>}
                <button type="button"><CloseIcon></CloseIcon><span className='sr-only'>Close alert message</span></button>
            </div>
        </div>
    )
}

export default AlertMessage;