
enum Price(val p:Float):
  case corn extends Price(0.05f)
  case chees extends Price(0.35f)
  case meat extends Price(0.25f)
  case fish extends Price(0.3f)
  case wool extends Price(0.4f)
  case flax extends Price(0.2f)
  case timber extends Price(0.18f)
  case leather extends Price(0.56f)
  case gold extends Price(199.0f)
  case silver extends Price(89.0f)
  case iron extends Price(30.0f)
  case coper extends Price(44.0f)
  case lead extends Price(38.0f)


@main
def main():Unit =
  for p <- Price.values do
    println(s"goods $p nr:${p.ordinal} cost is: ${p.p}")
