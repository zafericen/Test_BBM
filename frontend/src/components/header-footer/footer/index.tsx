import styles from "./index.module.scss";
import texts from "../../../styles/texts.scss";

const navbarSearchText = texts["navbar-search-text"];
const navbarCategoriesText = texts["navbar-categories-text"];
const navbarFavoritesText = texts["navbar-favorites-text"];
const navbarLoginText = texts["navbar-login-text"];

function Footer() {
    return (
        <footer className={styles.footer}>
            <img src="/logo-text-black.png" alt="ShopSmart" className={styles.logo}/>
            <p>©2024 NullPointerException - All Rights Reserved</p>
            <div className={styles.contact}>
                <div className={styles.text}><u>Contact Us:</u></div>
                <div className={styles.text}>tanercelikkiran@gmail.com</div>
                <div className={styles.text}>denizcanaksu121@gmail.com</div>
            </div>
        </footer>
    )
}

export default Footer;