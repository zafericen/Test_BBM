module.exports = {
    //mode: "production",
    mode: "development", devtool: "inline-source-map",

    entry: [ "./src/pages/app.tsx" ],
    output: {
        filename: "./output/bundle.js"  // in /dist
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".css", ".scss", ".sass"]
    },
    module: {
        rules: [
            { test: "/.tsx?$/", loader: "ts-loader" },
            { test: "/.scss?$/", use: [
                    { loader: "style-loader" },
                    { loader: "css-modules-typescript-loader" },
                    { loader: "css-loader", options: { modules: true } },
                    { loader: "sass-loader" }
                ] },
        ]
    }
}