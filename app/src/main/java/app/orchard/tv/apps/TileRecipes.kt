package app.orchard.tv.apps

/**
 * How Aerial redraws one app's tile when what the app ships reads badly on Home: a banner crowded
 * with text, or a phone icon whose plate the tile cannot carry. Everything comes from the app's own
 * icon; a recipe only says how to lay it out, so no artwork of another brand ships with Aerial.
 */
class TileRecipe(
    /** Built from the icon even when the app has a TV banner. */
    val useIcon: Boolean = false,
    /** The tile's colors, top to bottom, instead of the icon's own plate. */
    val top: Int? = null,
    val bottom: Int? = null,
    /** The logo drawn in white. Only for logos of one color on a plate of another. */
    val white: Boolean = false,
    /** The logo keeps its own colors on its own plate, outside Aerial's one look: a white logo would lose what tells it apart. */
    val keepColors: Boolean = false,
    /** Drawn as Aerial's own glyph over [top]..[bottom], for apps whose art is only their name. */
    val glyph: app.orchard.tv.ui.theme.Glyph? = null,
)

object TileRecipes {
    private val byPackage = mapOf(
        // Checked against the apps on Xiaomi MiTV-MFTR0.
        "com.mitv.tvhome" to TileRecipe(top = 0xFF4C8DFF.toInt(), bottom = 0xFF1F55E0.toInt(), white = true), // 桌面 (PatchWall)
        "com.duokan.videodaily" to TileRecipe(top = 0xFFFF4B4B.toInt(), bottom = 0xFFD0101C.toInt()), // 影片頭條: a picture, not a plate
        // Checked against the apps on TCL MT5879 (China), drawn on Xiaomi MiTV-MFTR0.
        "cn.wps.moffice_i18n_TV" to TileRecipe(top = 0xFFFF5A3C.toInt(), bottom = 0xFFD81E1E.toInt(), white = true), // WPS TV版
        "com.dangbeimarket" to TileRecipe(useIcon = true, keepColors = true), // 當貝市場: the banner repeats its name in two sizes; the logo is embossed
        "com.wtj.cn.alipantv" to TileRecipe(useIcon = true), // 阿里雲盤TV: the banner crowds its name into a corner
        "com.tcl.tshop" to TileRecipe(keepColors = true), // T惠購: the red ribbon is what makes it a gift
        "com.quark.yun.tv" to TileRecipe(top = 0xFF6A7BFF.toInt(), bottom = 0xFF3F3BE0.toInt(), white = true), // 誇克網盤
        // 我的電視: live channels; its icon and banner are only its name in grey. Teal as on tuner inputs.
        "com.lizongying.mytv" to TileRecipe(top = 0xFF1FA88E.toInt(), bottom = 0xFF0E5C55.toInt(), glyph = app.orchard.tv.ui.theme.Glyph.Antenna),
        // Checked against APKs from 當貝市場, drawn on Xiaomi MiTV-MFTR0.
        "com.baidu.netdisk.tv" to TileRecipe(useIcon = true), // 百度網盤: the banner adds its name and "TV版" beside the icon
        "com.dangbei.dbmusic" to TileRecipe(useIcon = true), // BesTV當貝音樂: name and slogan on a white banner
        "com.dangbei.tvlauncher" to TileRecipe(useIcon = true), // 當貝桌面: the banner is its name and a version badge
        "com.tencent.karaoketv" to TileRecipe(useIcon = true), // 全民K歌: name beside the icon on white
        "cn.jmake.karaoke.box.open" to TileRecipe(useIcon = true), // 雷石K歌: a cropped microphone over "專業版"
        "com.xunlei.downloadprovider.hd" to TileRecipe(useIcon = true), // 迅雷雲盤: name beside the icon on white
        "com.ucmobile.yun.tv" to TileRecipe(keepColors = true), // UC網盤: a shaded logo that turns to mist in white
        "com.zmg.ott" to TileRecipe(top = 0xFF3FA9FF.toInt(), bottom = 0xFF1747E0.toInt(), white = true), // Z視介: its white Z, without the facets behind it
        "com.sohuott.tv.vod" to TileRecipe(keepColors = true), // 雲視聽悅廳TV: a shaded logo that turns to mist in white
    )

    fun of(pkg: String): TileRecipe? = byPackage[pkg]
}
