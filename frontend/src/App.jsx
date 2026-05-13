import { useState } from "react";
import frontImg from "./assets/front.png";
import frontCloseImg from "./assets/front_Close.png";
import Header from "./components/Header";
import api from "./api/axios_config";
import "./css/App.css";

function App() {
  const [isDoorOpen, setIsDoorOpen] = useState(true);
  const [, setMenuOpen] = useState(false);
  const openTime = 12;
  const closeTime = 22;
  const currentTime = new Date().getHours();

const getTables = () => {
  api.get('/tables/test').then((response) => {
    console.log(response.data);
  });
}


  // Open or Close

  const isBusinessHours =
    currentTime >= openTime && currentTime < closeTime;

  const imageSrc =
    isBusinessHours && isDoorOpen ? frontImg : frontCloseImg;

  return (
    <main className="app">
      <div className="imageFrame">
        <img src={imageSrc} className="mainImg" alt="" />
        <Header onToggleMenu={() => setMenuOpen((prev) => !prev)} />
        <button
          className="menuHotspot"
          type="button" 
          aria-label="Open menu"
          onClick={() => {
            setMenuOpen((prev) => !prev);
            getTables();
          }}
        />
        <button
          className="doorHotspot"
          type="button"
          aria-label="Toggle door sign"
        />
      </div>
    </main>
  );
}

export default App;
