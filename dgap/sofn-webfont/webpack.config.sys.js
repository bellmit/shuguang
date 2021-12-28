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
var occurenceOrderPlugin = new webpack.optimize.OccurenceOrderPlugin();

// 删除重复或者相似的文件
var dedupePlugin = new webpack.optimize.DedupePlugin();

module.exports = {
    //页面入口文件配置
    entry: {
        codeRule: [
            path.join(__dirname, 'webapp/template/containers/codeRule')
        ],
        vendors: ['angular', 'ngreact', 'react', 'react-dom']
    },
    //入口文件输出配置
    output: {
        path: path.resolve(__dirname, "webapp/sys/sysCodeRuleAdd/"),
        filename: '[name]/[name].min.js?[hash]'
    },
    module: {
        //加载器配置
        rules: [{
            test: /\.jsx?$/,
            exclude: /(node_modules|bower_components)/,
            use: "babel-loader",
            options: {
                presets: ['react', 'stage-0', 'es2015']
            }
        }, {
            test: /\.less$/i,
            use: extractLESS.extract(['css?localIdentName=styles/[name]---[local]---[hash:base64:5]', 'less', 'postcss'])
            // loader: extractLESS.extract(['css?sourceMap&modules&localIdentName=[local]___[hash:base64:5]&-autoprefixer', 'postcss', 'less?{"sourceMap":true}'])

        }, {
            test: /\.css/,
            use: extractCSS.extract(['css?localIdentName=styles/[name]---[local]---[hash:base64:5]', 'postcss'])
        }, {
            test: /\.(png|jpg)$/,
            use: 'url-loader?limit=8192&name=images/[name].[ext]?[hash]'
        },{
            test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
            use: `${require.resolve('url-loader')}?` + `limit=10000&minetype=application/font-woff`
        }, {
            test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
            use: `${require.resolve('url-loader')}?` + `limit=10000&minetype=application/font-woff`
        }, {
            test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
            use: `${require.resolve('url-loader')}?` + `limit=10000&minetype=application/octet-stream`
        }, {
            test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
            use: `${require.resolve('file-loader')}`
        }, {
            test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
            use: `${require.resolve('url-loader')}?` + `limit=10000&minetype=image/svg+xml`
        }, {
            test: /\.json$/,
            use: `${require.resolve('json-loader')}`
        }, {
            test: /\.html?$/,
            use: `${require.resolve('file-loader')}?name=[name].[ext]`
        }],
        noParse: []
    },
    //插件项
    plugins: [
        DefinePlugin,
        commonsPlugin,
        extractCSS,
        extractLESS,
        uglifyJsPlugin,
        occurenceOrderPlugin,
        dedupePlugin,
        new htmlWebpackPlugin({
            // title: '',
            template: path.resolve(__dirname, 'template.ejs'),
            filename: 'codeRule/index.html',
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ['vendors', 'codeRule'],
            hash: true,
            // inject: 'body',
            minify: {}
        })
    ],
    resolve: {
        extensions: ['', '.js', '.jsx', '.json', '.less', '.css', '.png', '.jpg'],
        //别名
        alias: {}
    },
    // 启用source-map
    debug: false,
    postcss: function() {
        return [
          require('rucksack-css'),
          require('autoprefixer')({browsers: ['last 2 versions', 'Firefox ESR', '> 1%', 'ie >= 9']}),
          // require('cssnano')(),
          // require('postcss-sorting')({ /* options */ })
        ];
    }
};
