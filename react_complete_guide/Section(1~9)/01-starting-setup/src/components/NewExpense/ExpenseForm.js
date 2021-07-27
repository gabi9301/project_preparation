import React, { useState } from 'react';
import './ExpenseForm.css';

const ExpenseForm = (props) => {
    


    const [enteredTitle, setEnteredTitle] = useState('');
    const [enteredAmout, setEnteredAmount] = useState('');
    const [enteredDate, setEnteredDate] = useState('');
    const [showExpenseForm, setShowExpenseForm] = useState(false);

    const titleChangeHandler = event => {
        setEnteredTitle(event.target.value);
    };

    const amountChangeHandler = event => {
        setEnteredAmount(event.target.value);  
    };

    const dateChangeHandler = event => {
        setEnteredDate(event.target.value);
    };

    const showExpenseFormToggle = () => {
      setShowExpenseForm(!showExpenseForm);
    };

    const submitHandler = (event) => {
        event.preventDefault();

        const expenseData = {
            title: enteredTitle,
            amount: +enteredAmout,
            date: new Date(enteredDate)
        };

        props.onSaveExpenseData(expenseData);
        setEnteredTitle('');
        setEnteredAmount('');
        setEnteredDate('');

        showExpenseFormToggle();
   };

    let initialContent = <div>
          <button onClick={showExpenseFormToggle}>
            Add New Expense
          </button>
        </div>;

    let expenseForm = <form onSubmit={submitHandler}>
          <div className="new-expense__controls">
            <div className="new-expense__control">
              <label>Title</label>
              <input
                type="text"
                value={enteredTitle}
                onChange={titleChangeHandler}
              />
            </div>
            <div className="new-expense__control">
              <label>Amount</label>
              <input
                type="number"
                min="0.01"
                step="0.01"
                value={enteredAmout}
                onChange={amountChangeHandler}
              />
            </div>
            <div className="new-expense__control">
              <label>Date</label>
              <input
                type="date"
                min="2019-01-01"
                max="2022-12-31"
                value={enteredDate}
                onChange={dateChangeHandler}
              />
            </div>
          </div>
          <div className="new-expense__actions">
            <button type="submit">Add Expense</button>
            <button onClick={showExpenseFormToggle}>Cancel</button>
          </div>
        </form>;

    return <div>{showExpenseForm ? expenseForm : initialContent}</div>;

};

export default ExpenseForm;