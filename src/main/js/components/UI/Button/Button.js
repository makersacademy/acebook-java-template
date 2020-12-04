import React from 'react';

const Button = (props) => (
    <button
        disabled={props.disabled}
        hidden={props.hidden}
        className={["Button", props.btnType].join(" ")}
        onClick={props.clicked}>
      {props.children}
    </button>
)

export default Button;