import React, { useState } from 'react';

import Card from "../UI/Card";
import "./Expenses.css";
import ExpenseFilter from "./ExpenseFilter";
import ExpensesList from "./ExpensesList";
import ExpensesChart from './ExpensesChart';


const Expenses = (props) => {
    const [enteredFilterData, setEnteredFilterData] = useState('2021');

    const displayFilterDataHandler = filterData => {
      setEnteredFilterData(filterData);
    };

    const filteredExpenses = props.items.filter(expense => {
      return expense.date.getFullYear().toString() === enteredFilterData;
    });

    

    return (
      <Card className="expenses">
        <ExpenseFilter
          selected={enteredFilterData}
          onDisplayFilterData={displayFilterDataHandler}
        />
        <ExpensesChart expenses={filteredExpenses} />
        <ExpensesList items={filteredExpenses} />
      </Card>
    );
}

export default Expenses;
