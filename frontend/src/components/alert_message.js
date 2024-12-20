const AlertMessage = ({message}) => {
    return (
        <div className={`sentiment ${message ? 'active' : ''}`} aria-live="polite" id="sentimentMessage">
            {message && <p className="sentiment__message">{message}</p>}
        </div>
    )
}

export default AlertMessage;