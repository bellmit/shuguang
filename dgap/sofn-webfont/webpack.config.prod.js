var webpack = require('webpack');
var path = require('path');
var node_modules = path.resolve(__dirname, 'node_modules');

var htmlWebpackPlugin = require('html-webpack-plugin');

//将css生成文件，而非内联
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var extractLESS = new ExtractTextPlugin('[name].min.css');
var extractCSS = new ExtractTextPlugin('[name].common.min.css');

//提取通用部分
var commonsPlugin = new webpack.optimize.CommonsChunkPlugin({ name: 'vendors', filename: 'vendors.min.js' });

//变量定义
var DefinePlugin = new webpack.DefinePlugin({
    "process.env": {
        NODE_ENV: JSON.stringify("production")
    }
});

//js压缩
var uglifyJsPlugin = new webpack.optimize.UglifyJsPlugin({
    screw_ie8: true,
    sourceMap: false,
    mangle: false,
    comments: false,
    compress: {
        warnings: false,
        drop_console: true
    },
    mangle: {
        except: ['$super', '$', 'exports', 'require']
    }
});

//顺序
// var occurenceOrderPlugin = new webpack.optimize.OccurenceOrderPlugin();

module.exports = {
    //页面入口文件配置
    entry: {
        field: [
            path.join(__dirname, 'webapp/template/containers/field')
        ],
        review: [
            path.join(__dirname, 'webapp/template/containers/review')
        ],
        structure: [
            path.join(__dirname, 'webapp/template/containers/structure')
        ],
        vendors: ['angular', 'ngreact', 'react', 'react-dom']
    },
    //入口文件输出配置
    output: {
        path: path.resolve(__dirname, "webapp/dgap/resource_operation"),
        filename: '[name]/[name].min.js?[hash]'
    },
    module: {
        //加载器配置
        rules: [{
            test: /\.jsx?$/,
            exclude: /(node_modules|bower_components)/,
            use: ["babel-loader"]
        }, {
            test: /\.less$/i,
            use: extractLESS.extract(['css-loader', 'less-loader'])
        }, {
            test: /\.css$/,
            use: extractCSS.extract(['css-loader', 'postcss-loader'])
        }, {
            test: /\.(png|jpg)$/,
            use: [{ loader: 'url-loader', options: { limit: 8192, name: 'images/[name].[ext]?[hash]' } }]
        }]
    },
    //插件项
    plugins: [
        DefinePlugin,
        commonsPlugin,
        extractCSS,
        extractLESS,
        uglifyJsPlugin,
        new webpack.LoaderOptionsPlugin({
            options: {
                postcss: function() {
                    return [
                        require('rucksack-css'),
                        require('autoprefixer')({ browsers: ['last 2 versions', 'Firefox ESR', '> 1%', 'ie >= 9'] }),
                        // require('cssnano')(),
                        // require('postcss-sorting')({ /* options */ })
                    ];
                }
            }
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, 'template.ejs'),
            filename: 'field/index.html',
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ['vendors', 'field'],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, 'template.ejs'),
            filename: 'review/index.html',
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ['vendors', 'review'],
            hash: true,
            // inject: 'body',
            minify: {}
        }),
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, 'template.ejs'),
            filename: 'structure/index.html',
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ['vendors', 'structure'],
            hash: true,
            // inject: 'body',
            minify: {}
        })
    ],
    resolve: {
        extensions: ['.js', '.jsx', '.json', '.less', '.css', '.png', '.jpg'],
        //别名
        alias: {}
    }
};