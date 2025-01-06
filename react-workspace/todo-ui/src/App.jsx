import { useState } from 'react'
import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import {BrowserRouter,Routes,Route} from 'react-router-dom'
import TodoComponent from './components/TodoComponent'



function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
    <HeaderComponent />

    <Routes>
     {/* http://localhost:8080  */}
<Route path='/' element={<ListTodoComponent/>}></Route>
<Route path='/todos' element={<ListTodoComponent/>}></Route>
<Route path='/add-todo' element={<TodoComponent/>}></Route>
<Route path='/update-todo/:id' element={<TodoComponent/>}></Route>


    </Routes>

  <FooterComponent />  
  </BrowserRouter>
    </>
  )
}

export default App
