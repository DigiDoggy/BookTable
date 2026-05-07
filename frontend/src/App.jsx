import { useState } from "react";
import frontImg from "./assets/front.png";
import frontCloseImg from "./assets/front_Close.png";
import "./App.css";

function App() {
  const [isDoorOpen, setIsDoorOpen] = useState(true);

  return (
    <main className="app">
      <div className="imageFrame">
        <img
          src={isDoorOpen ? frontImg : frontCloseImg}
          className="mainImg"
          alt="Главное изображение"
        />
        <button className="menuHotspot" type="button" aria-label="Open menu" />
        <button
          className="doorHotspot"
          type="button"
          aria-label="Toggle door sign"
          onClick={() => setIsDoorOpen((prev) => !prev)}
        />
      </div>
    </main>
  );
}

export default App;
