import styles from "./index.module.scss";
import texts from "../../../../styles/texts.scss";
import React, {Dispatch, SetStateAction} from "react";

const navbarSearchText = texts["navbar-search-text"];
const navbarCategoriesText = texts["navbar-categories-text"];
const navbarDashboardText = texts["navbar-dashboard-text"];
const navbarProfileText = texts["navbar-profile-text"];

function HeaderOther({pageFunc}: {pageFunc: Dispatch<SetStateAction<string>>}) {

    const [searchQuery, setSearchQuery] = React.useState('');

    return (
        <header className={styles.header}>
            <img src="/logo-text-black.png" alt="ShopSmart" className={styles.logo} onClick={(e) => pageFunc("main")}/>

            <div className={"search-bar-container"}>
                <input type={"text"} placeholder={navbarSearchText} value={searchQuery}
                       onChange={(e) => setSearchQuery(e.target.value)}
                       className={styles["search-bar"]}/>
                <img src="/search.png" alt="Search" className={styles["search-icon"]}/>
            </div>

            <button className={styles["categories-button"]} >
                {navbarCategoriesText}
                <img src="/categories.png" alt="Categories" className={styles["icon"]}/>
            </button>

            <button className={styles["dashboard-button"]}>
                {navbarDashboardText}
                <img src="/dashboard.png" alt="Your Dashboard" className={styles["icon"]}/>
            </button>

            <button className={styles["profile-button"]} onClick={(e) => pageFunc("login-signup")}>
                {navbarProfileText}
                <img src="/avatar.png" alt="Your Profile" className={styles["icon"]}/>
            </button>
        </header>
    )
}

export default HeaderOther;