import gulp from "gulp";
import less from "gulp-less";
import rename from "gulp-rename";
import plumber from "gulp-plumber";
import notify from "gulp-notify";
import cleanCSS from "gulp-clean-css";
import LessPluginAutoPrefix from "less-plugin-autoprefix";
import buffer from "vinyl-buffer";
import imagemin from "gulp-imagemin";
import merge from "merge-stream";
import spritesmith from "gulp.spritesmith";
import path from "path";
import del from "del";

const paths = {
	styles: {
		src: "assets/styles/**/*.less",
		dest: "./"
	},
	images: {
		dir: "common",
		src: "assets/imgs/common/*.png",
		imgDest: "assets/imgs/dist/",
		cssDest: "assets/styles/common/"
	}
};

/*
 * You can also declare named functions and export them as tasks
 */

let autoprefix = new LessPluginAutoPrefix({ browsers: ["last 2 versions"] });

export function styles() {
	return (
		gulp
			.src(paths.styles.src, { base: process.cwd() })
			.pipe(
				plumber({
					errorHandler: notify.onError("Error: <%= error.message %>")
				})
			)
			.pipe(
				less({
					paths: [path.join(__dirname, "less", "includes")],
					plugins: [autoprefix]
				})
			)
			// .pipe(cleanCSS({ compatibility: 'ie9' })) //sprite img path issue
			.pipe(gulp.dest(paths.styles.dest))
	);
}

export function sprite() {
	// Generate our spritesheet
	var spriteData = gulp.src(paths.images.src).pipe(
		spritesmith({
			imgName: "sprite.png",
			cssName: "sprite.less",
			imgPath: "../../imgs/dist/sprite.png",
			cssVarMap: function(sprite) {
				sprite.name = sprite.name.replace(/^\s{1}/, ""); //去除文件名前空白
				sprite.name = sprite.name.replace("@2x", "");
				sprite.name = sprite.name.replace(/^\d\./, "");
				sprite.name = sprite.name.toLowerCase();
				sprite.name = ~sprite.name.indexOf("icon-")
					? sprite.name
					: "icon-" + sprite.name;
			},
			algorithm: "binary-tree",
			// 可选: 指定CSS格式 (默认根据destCSS中的后缀设置格式)
			// (stylus, scss, scss_maps, sass, less, json, json_array, css)
			cssFormat: "less",
			padding: 10
		})
	);

	// Pipe image stream through image optimizer and onto disk
	var imgStream = spriteData.img
		// DEV: We must buffer our stream into a Buffer for `imagemin`
		.pipe(buffer())
		.pipe(imagemin())
		.pipe(gulp.dest(paths.images.imgDest));

	// Pipe CSS stream through CSS optimizer and onto disk
	var cssStream = spriteData.css.pipe(gulp.dest(paths.images.cssDest));

	// Return a merged stream to handle both `end` events
	return merge(imgStream, cssStream);
}

export function compress() {
	gulp.src("assets/imgs/**/*")
		.pipe(imagemin())
		.pipe(gulp.dest("dist/images"));
}

/*
 * You could even use `export as` to rename exported tasks
 */
function watchFiles() {
	gulp.watch(paths.styles.src, styles);
}
export { watchFiles as watch };

const build = gulp.series(styles);
gulp.task("build", build);
gulp.task("sprite", sprite);
gulp.task("compress", compress);

/*
 * Export a default task
 */
export default watchFiles;
