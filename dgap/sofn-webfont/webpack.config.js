var webpack = require("webpack");
var path = require("path");
var node_modules = path.resolve(__dirname, "node_modules");
var htmlWebpackPlugin = require("html-webpack-plugin");

//变量定义
var DefinePlugin = new webpack.DefinePlugin({
    "process.env": {
        NODE_ENV: JSON.stringify("development")
    }
});

//挂载到全局
var providePlugin = new webpack.ProvidePlugin({
    angular: "angular",
    React: "react",
    ReactDOM: "react-dom"
});
module.exports = {
    mode: "development",
    //页面入口文件配置
    entry: {
        field: [path.join(__dirname, "webapp/template/containers/field")],
        review: [path.join(__dirname, "webapp/template/containers/review")],
        structure: [
            path.join(__dirname, "webapp/template/containers/structure")
        ],
        codeRule: [path.join(__dirname, "webapp/template/containers/codeRule")],
        vendors: ["angular", "react", "react-dom", "ngreact"]
    },
    //入口文件输出配置
    output: {
        path: path.resolve(__dirname, "webapp/dgap/resource_operation"),
        // publicPath: '/webapp/template/views/',
        publicPath: "/",
        filename: "[name].min.js"
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/preset-env"]
                    }
                }
            },
            {
                test: /\.less/,
                use: [
                    {
                        loader: "style-loader"
                    },
                    {
                        loader: "css-loader"
                    },
                    {
                        loader: "less-loader",
                        options: {
                            javascriptEnabled: true
                        }
                    }
                ]
            },
            {
                test: /\.css/,
                use: ["style-loader", "css-loader"]
            },
            {
                test: /\.(png|jpg|jpeg|gif)$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 8192,
                            name: "images/[name].[ext]?[hash]"
                        }
                    }
                ]
            },
            {
                test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 10000,
                            minetype: "application/font-woff"
                        }
                    }
                ]
            },
            {
                test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 10000,
                            minetype: "application/font-woff"
                        }
                    }
                ]
            },
            {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 10000,
                            minetype: "application/octet-stream"
                        }
                    }
                ]
            },
            {
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                use: ["file-loader"]
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            limit: 10000,
                            minetype: "image/svg+xml"
                        }
                    }
                ]
            },
            {
                test: /\.json$/,
                use: ["file-loader"]
            },
            {
                test: /\.html?$/,
                use: [
                    {
                        loader: "url-loader",
                        options: {
                            name: "[name].[ext]"
                        }
                    }
                ]
            }
        ]
    },
    //插件项
    plugins: [
        DefinePlugin,
        new webpack.NamedModulesPlugin(),
        new webpack.LoaderOptionsPlugin({
            debug: true
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, "template.ejs"),
            filename: "field.html",
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ["vendors", "field"],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, "template.ejs"),
            filename: "review.html",
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ["vendors", "review"],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, "template.ejs"),
            filename: "structure.html",
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ["vendors", "structure"],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, "template.ejs"),
            filename: "codeRule.html",
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ["vendors", "codeRule"],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new webpack.HotModuleReplacementPlugin() //热替换
    ],
    resolve: {
        extensions: [".js", ".jsx", ".json", ".less", ".css", ".png", ".jpg"],
        //别名
        alias: {}
    },
    //监视变化
    // watch: true,
    // 启用source-map
    devtool: "#source-map",
    devServer: {
        contentBase: "./",
        hot: true,
        // https: true,
        compress: true,
        host: "localhost",
        // host: "0.0.0.0",
        open: true,
        headers: {
            "X-Custom-Header": "yes"
        },
        overlay: {
            warnings: true,
            errors: true
        },
        stats: "normal",
        proxy: {
            //各子系统代理配置
            "/sofn-sso-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-qry-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-tts-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-sys-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-asms-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-ales-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-ads-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-ssa-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-dgap-web/*": {
                target: "http://218.89.222.117:9006/",
                // target: 'http://10.0.50.167/',
                secure: false
            },
            "/sofn-query/*": {
                target: "http://218.89.222.117:9006/",
                secure: false
            },
            "/sofn-etl-query/*": {
                target: "http://218.89.222.117:9006/",
                secure: false
            }
        }
    }
};
