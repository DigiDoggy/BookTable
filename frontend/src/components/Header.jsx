import "../css/Header.css";

function Header({ onToggleMenu }) {
  return (
    <div className="header">
      <button
        type="button"
        className="arrow-btn"
        aria-label="Open menu"
        onClick={onToggleMenu}
      />
    </div>
  );
}

export default Header;
