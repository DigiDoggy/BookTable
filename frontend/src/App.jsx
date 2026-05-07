import frontImg from "./assets/front.png";
import "./App.css";

function App() {
  return (
    <main className="app">
      <div className="imageFrame">
        <img src={frontImg} className="mainImg" alt="Главное изображение" />
        <button className="menuHotspot" type="button">
          Открыть меню
        </button>
      </div>
    </main>
  );
}

export default App;
