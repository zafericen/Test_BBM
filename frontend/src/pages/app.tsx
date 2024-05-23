import React, {useState} from "react";
import HeaderNotLogged from "../components/header-footer/header/header-not-logged/index";
import HeaderOther from "../components/header-footer/header/header-other/index";
import HeaderUser from "../components/header-footer/header/header-user/index";
import MerchantDashboardPage from "./merchant-dashboard-page";
import Footer from "../components/header-footer/footer";
import ProductPage from "./product-page";
import LoginSignupPage from "./login-signup-page";
import MainPage from "./main-page";
import AddProductPage from "./add-product-page";
import AddCommentPage from "./add-comment-page";
import EditProductPage from "./edit-product-page";
import EditCommentPage from "./edit-comment-page";

function App() {

    const [page, setPage] = useState("main");
    const [product, setProduct] = useState("null");
    const [user, setUser] = useState("null");

    const component = () => {
        switch (page) {
            case "login-signup":
                return <LoginSignupPage userIDFunc={setUser} pageFunc={setPage}/>;
            case "add-product":
                return <AddProductPage userId={user} pageFunc={setPage} productFunc={setProduct}/>;
            case "edit-product":
                return <EditProductPage userId={user} pageFunc={setPage} productFunc={setProduct}/>;
            case "add-comment":
                return <AddCommentPage userId={user} productId={product} pageFunc={setPage} />;
            case "edit-comment":
                return <EditCommentPage userId={user} productId={product} pageFunc={setPage} />;
            case "merchant-dashboard":
                return <MerchantDashboardPage userId={user} pageFunc={setPage} productFunc={setProduct}/>;
            case "product":
                return <ProductPage productId={product} pageFunc={setPage}/>;
            default:
                return <MainPage userId={user} pageFunc={setPage} productFunc={setProduct}/>;
        }
    }

    const header = () => {
        switch (user) {
            case "null":
                return <HeaderNotLogged pageFunc={setPage}/>;
            default:
                return <HeaderUser pageFunc={setPage}/>;
        }
    }

    return (
        <div className="app-root">
            {header()}
            {component()}
            <Footer/>
        </div>
    )
}

export default App;