import React, { useState, useEffect, useReducer, useContext } from 'react';

import Card from '../UI/Card/Card';
import classes from './Login.module.css';
import Button from '../UI/Button/Button';
import AuthContext from '../../store/auth-context';
import Input from '../UI/Input/Input';


const emailReducer = (state, action) => {
  if(action.type === 'USER_INPUT') {
    return {value:action.val, isValid: action.val.includes('@')};
  }

  if(action.type === 'INPUT_BLUR') {
    return {value: state.value, isValid: state.value.includes('@')};
  }
  
  return {value: '', isValid: false};

};  

const passwordReducer = (state, action) => {
  if(action.type === 'USER_INPUT') {
    return {value:action.val, isValid: action.val.trim().length > 6}
  }

  if(action.type === 'INPUT_BLUR') {
    return {value: state.value, isValid: state.value.trim().length > 6}
  }

  return {value: '', isValid: false}

};
//reducer 함수를 컴포넌트 함수 밖에서 선언할 수 있는 이유는 컴포넌트 함수로 생겨난 
//어떠한 데이터도 필요로 하지 않기 때문이다.
//reducer 함수가 필요로 하는 데이터는 해당 함수를 실행할 때 리액트가 자동으로 넘겨준다.



const Login = (props) => {
  // const [enteredEmail, setEnteredEmail] = useState('');
  // const [emailIsValid, setEmailIsValid] = useState();
  // const [enteredPassword, setEnteredPassword] = useState('');
  // const [passwordIsValid, setPasswordIsValid] = useState();
  const [formIsValid, setFormIsValid] = useState(false);

  const [emailState, dispatchEmail] = useReducer(emailReducer, {value: '', isValid: null});

  const [passwordState, dispatchPassword] = useReducer(passwordReducer, {value: '', isValid: null});

  const authCtx = useContext(AuthContext);


  const {isValid: emailIsValid} = emailState;
  const {isValid: passwordIsValid} = passwordState;

  useEffect(() => {
    console.log('EFFECT RUNNING');

    return () => {
      console.log('EFFECT CLEANUP');
    };
  }, []);

  useEffect(() => {
    const identifier = setTimeout(() => {
      console.log('Checking form validity!');
      setFormIsValid(
        emailIsValid && passwordIsValid
      );
    }, 500);

    return () => {
      console.log('CLEANUP');
      clearTimeout(identifier);
    };
  }, [emailIsValid, passwordIsValid]);

  const emailChangeHandler = (event) => {   //이메일에 적히는 값이 바뀔 때
    dispatchEmail({type:'USER_INPUT', val : event.target.value});

    setFormIsValid(
      emailState.isValid && passwordState.isValid
    );
  };

  const passwordChangeHandler = (event) => {  //패스워드에 적히는 값이 바뀔 때
    dispatchPassword({type: 'USER_INPUT', val: event.target.value});

    setFormIsValid(
      emailState.isValid && passwordState.isValid
    );
  };

  const validateEmailHandler = () => {    //이메일에 적힌 값이 유효하지 않을 때
    dispatchEmail({type: 'INPUT_BLUR'});
  };

  const validatePasswordHandler = () => {
    dispatchPassword({type: 'INPUT_BLUR'});
  };

  const submitHandler = (event) => {
    event.preventDefault();
    authCtx.onLogin(emailState.value, passwordState.value);
  };

  return (
    <Card className={classes.login}>
      <form onSubmit={submitHandler}>
        <Input 
          id="email" 
          label="E-Mail" 
          type="email" 
          isValid={emailIsValid} 
          value={emailState.value}
          onChange={emailChangeHandler}
          onBlur={validateEmailHandler}/>
        <Input 
          id="password" 
          label="Password" 
          type="password" 
          isValid={passwordIsValid} 
          value={passwordState.value}
          onChange={passwordChangeHandler}
          onBlur={validatePasswordHandler}/>
        <div className={classes.actions}>
          <Button type="submit" className={classes.btn} disabled={!formIsValid}>
            Login
          </Button>
        </div>
      </form>
    </Card>
  );
};

export default Login;
