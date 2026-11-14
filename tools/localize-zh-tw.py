#!/usr/bin/env python3
"""Convert Aerial's Chinese UI text to Traditional Chinese, then apply Taiwan wording."""
from pathlib import Path
import subprocess

ROOT = Path(__file__).resolve().parents[1]
FILES = [
    ROOT / "app/src/main/res/values-zh-rTW/strings.xml",
    ROOT / "app/src/main/assets/web/index.html",
]
FILES += list((ROOT / "app/src/main/java").rglob("*.kt"))
FILES += list((ROOT / "lite/src/main/java").rglob("*.kt"))

# Convert Simplified Chinese characters to Traditional using ICU, then prefer Taiwan terms.
TERMS = [
    ("文件夾", "資料夾"), ("文件夹", "資料夾"),
    ("文件", "檔案"), ("軟件", "軟體"), ("软件", "軟體"),
    ("網絡", "網路"), ("网络", "網路"),
    ("視頻", "影片"), ("视频", "影片"),
    ("屏幕", "螢幕"), ("用戶", "使用者"), ("用户", "使用者"),
    ("信息", "資訊"), ("數據", "資料"), ("数据", "資料"),
    ("打印", "列印"), ("鼠標", "滑鼠"), ("鼠标", "滑鼠"),
    ("服務器", "伺服器"), ("服务器", "伺服器"),
    ("默認", "預設"), ("默认", "預設"),
    ("應用程序", "應用程式"), ("应用程序", "應用程式"),
    ("安裝程序", "安裝程式"), ("安装程序", "安裝程式"),
    ("主屏幕", "主畫面"), ("主屏幕", "主畫面"),
    ("網頁", "網頁"), ("網絡連接", "網路連線"),
    ("网络连接", "網路連線"), ("連接", "連線"), ("连接", "連線"),
    ("視頻播放", "影片播放"), ("视频播放", "影片播放"),
    ("照片", "相片"), ("圖片", "圖片"),
]
def convert(path: Path) -> None:
    original = path.read_text(encoding="utf-8")
    converted = subprocess.run(
        ["uconv", "-x", "Simplified-Traditional"],
        input=original, text=True, encoding="utf-8", capture_output=True, check=True
    ).stdout
    for source, target in TERMS:
        converted = converted.replace(source, target)
    if converted != original:
        path.write_text(converted, encoding="utf-8")
        print(f"converted: {path.relative_to(ROOT)}")

for path in FILES:
    if path.exists():
        convert(path)
