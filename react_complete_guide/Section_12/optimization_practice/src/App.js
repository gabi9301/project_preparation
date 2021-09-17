import React, { useState, useCallback } from 'react';

import Button from './components/UI/Button/Button';
import './App.css';
import DemoOutput from './components/Demo/DemoOutput';

function App() {
  const [showParagraph, setShowParagraph] = useState(false);
  const [allowToggle, setAllowToggle] = useState(false);

  console.log("App Running!!!");

  const toggleButtonHandler = useCallback(() => {
    if (allowToggle) {
      setShowParagraph((prevShowParagraph) => !prevShowParagraph);
    }
  }, [allowToggle]);

  const allowToggleHanler = () => {
    setAllowToggle(true);
  };

  return (
    <div className="app">
      <h1>Hi there!</h1>
      <DemoOutput show={showParagraph} />
      <Button onClick={allowToggleHanler}>Allow Toggling</Button>
      <Button onClick={toggleButtonHandler}>Toggle Paragraph!</Button>
    </div>
  );
}

export default App;
